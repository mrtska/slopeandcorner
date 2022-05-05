package net.mrtska.slopeandcorner.model;


import com.google.common.primitives.Ints;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SlopeModelEntry {

    public static class SlopeModelVertex {

        public float x;
        public float y;
        public float z;
        public float u;
        public float v;
        private final Direction side;

        private SlopeModelVertex(float x, float y, float z, float u, float v, Direction side) {

            this.x = x;
            this.y = y;
            this.z = z;
            this.u = u;
            this.v = v;
            this.side = side;
        }

        private int getFaceShadeColor(Direction facing) {
            float f = this.getFaceBrightness(facing);
            int i = Mth.clamp((int) (f * 255.0F), 0, 255);
            return -16777216 | i << 16 | i << 8 | i;
        }

        private int getFaceDefaultBrightness(Direction side) {
            return switch (side) {
                case DOWN -> 0xFF888888;
                case UP -> 0xFFFFFFFF;
                case NORTH, SOUTH -> 0xFFCCCCCC;
                case EAST, WEST -> 0xFF999999;
            };
        }
        private float getFaceBrightness(Direction facing) {
            return switch (facing) {
                case DOWN -> 0.5F;
                case UP -> 1.0F;
                case NORTH, SOUTH -> 0.8F;
                case WEST, EAST -> 0.6F;
            };
        }

        private int[] vertexToInts(TextureAtlasSprite texture) {

            return new int[] {
                    Float.floatToRawIntBits(x),
                    Float.floatToRawIntBits(y),
                    Float.floatToRawIntBits(z),
                    getFaceShadeColor(side),
                    Float.floatToRawIntBits(texture.getU(u)),
                    Float.floatToRawIntBits(texture.getV(v)),
                    0, 0
            };
        }
    }

    public static class SlopeModelQuad {

        //頂点データ 面ごとに管理する
        private final ArrayList<SlopeModelVertex> vertex = new ArrayList<>();
        private Direction side;


        private SlopeModelQuad(SlopeModelVertex[] vertex, Direction side) {

            Collections.addAll(this.vertex, vertex);
            this.side = side;
        }

        public static SlopeModelQuad make(float x1, float y1, float z1, float u1, float v1,
                                          float x2, float y2, float z2, float u2, float v2,
                                          float x3, float y3, float z3, float u3, float v3,
                                          float x4, float y4, float z4, float u4, float v4, Direction side) {

            return new SlopeModelQuad(new SlopeModelVertex[] { new SlopeModelVertex(x1, y1, z1, u1, v1, side), new SlopeModelVertex(x2, y2, z2, u2, v2, side),
                    new SlopeModelVertex(x3, y3, z3, u3, v3, side), new SlopeModelVertex(x4, y4, z4, u4, v4, side) }, side);
        }

        public SlopeModelQuad add(float x, float y, float z, float u, float v) {

            vertex.add(new SlopeModelVertex(x, y, z, u, v, side));
            return this;
        }
    }

    public static class SlopeModelBlock {

        private final ArrayList<SlopeModelQuad> vertex;

        public SlopeModelBlock() {

            this.vertex = new ArrayList<SlopeModelQuad>();
        }

        public void add(Direction side, SlopeModelQuad quad) {

            quad.side = side;
            vertex.add(quad);
        }

        public List<BakedQuad> makeBakedQuad(TextureAtlasSprite... texture) {

            List<BakedQuad> ret = new ArrayList<>();


            Iterator<SlopeModelQuad> itr = this.vertex.iterator();
            int i = 0;
            while(itr.hasNext()) {

                SlopeModelQuad quad = itr.next();
                i = switch (quad.side) {
                    case DOWN -> 0;
                    case UP -> 1;
                    case NORTH -> 2;
                    case EAST -> 3;
                    case SOUTH -> 4;
                    case WEST -> 5;
                };

                SlopeModelVertex vertex1 = quad.vertex.get(0);
                SlopeModelVertex vertex2 = quad.vertex.get(1);
                SlopeModelVertex vertex3 = quad.vertex.get(2);
                SlopeModelVertex vertex4 = quad.vertex.get(3);

                if(texture[i].getName().toString().equals("minecraft:block/grass_block_top")) {

                    ret.add(new BakedQuad(Ints.concat(vertex1.vertexToInts(texture[i]), vertex2.vertexToInts(texture[i]), vertex3.vertexToInts(texture[i]), vertex4.vertexToInts(texture[i])), 1, quad.side, texture[i], false));
                } else {

                    ret.add(new BakedQuad(Ints.concat(vertex1.vertexToInts(texture[i]), vertex2.vertexToInts(texture[i]), vertex3.vertexToInts(texture[i]), vertex4.vertexToInts(texture[i])), -1, quad.side, texture[i], false));
                }
                if(texture[i].getName().toString().equals("minecraft:block/grass_block_side")) {

                    var side = SlopeModelBase.getTextureAtlasSprite("grass_block_side_overlay");
                    ret.add(new BakedQuad(Ints.concat(vertex1.vertexToInts(side), vertex2.vertexToInts(side), vertex3.vertexToInts(side), vertex4.vertexToInts(side)), 1, quad.side, texture[i], false));
                }
            }
            return ret;
        }
    }
}