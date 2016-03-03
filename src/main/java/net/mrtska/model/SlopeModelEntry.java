package net.mrtska.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

import com.google.common.primitives.Ints;

/**[Module SlopeModelEntry.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/05/06
*/
public class SlopeModelEntry {

	//斜面ブロックのモデルデータを管理するクラス

	public static class SlopeModelVertex {

		//座標 向き
		public float x;
		public float y;
		public float z;
		public float u;
		public float v;
		private EnumFacing side;

		private SlopeModelVertex(float x, float y, float z, float u, float v, EnumFacing side) {

			this.x = x;
			this.y = y;
			this.z = z;
			this.u = u;
			this.v = v;
			this.side = side;
		}

		//向きによって明るさを変える
		private int getFaceBrightness(EnumFacing side) {
			switch(side) {
			case DOWN:
				return 0xFF888888;
			case UP:
				return 0xFFFFFFFF;
			case NORTH:
			case SOUTH:
				return 0xFFCCCCCC;
			case EAST:
			case WEST:
				return 0xFF999999;
			default:
				return 0xFFFFFFFF;
			}
		}
		private int[] vertexToInts(TextureAtlasSprite texture) {
			return new int[] {
					Float.floatToRawIntBits(x),
					Float.floatToRawIntBits(y),
					Float.floatToRawIntBits(z),
					getFaceBrightness(side),
					Float.floatToRawIntBits(texture.getInterpolatedU(u)),
					Float.floatToRawIntBits(texture.getInterpolatedV(v)),
					0
			};
		}

	}

	public static class SlopeModelQuad {

		//頂点データ 面ごとに管理する
		private ArrayList<SlopeModelVertex> vertex = new ArrayList<SlopeModelVertex>();
		private EnumFacing side;


		private SlopeModelQuad(SlopeModelVertex[] vertex, EnumFacing side) {

			for(SlopeModelVertex ver : vertex) {

				this.vertex.add(ver);
			}
		}

		public static SlopeModelQuad make(float x1, float y1, float z1, float u1, float v1,
				float x2, float y2, float z2, float u2, float v2,
				float x3, float y3, float z3, float u3, float v3,
				float x4, float y4, float z4, float u4, float v4, EnumFacing side) {

			return new SlopeModelQuad(new SlopeModelVertex[] { new SlopeModelVertex(x1, y1, z1, u1, v1, side), new SlopeModelVertex(x2, y2, z2, u2, v2, side),
					new SlopeModelVertex(x3, y3, z3, u3, v3, side), new SlopeModelVertex(x4, y4, z4, u4, v4, side) }, side);
		}

		public SlopeModelQuad add(float x, float y, float z, float u, float v) {

			vertex.add(new SlopeModelVertex(x, y, z, u, v, side));
			return this;
		}
	}

	public static class SlopeModelBlock {

		private ArrayList<SlopeModelQuad> vertex;

		public SlopeModelBlock() {

			this.vertex = new ArrayList<SlopeModelQuad>();
		}


		public void add(EnumFacing side, SlopeModelQuad quad) {

			quad.side = side;
			vertex.add(quad);
		}


		public List<BakedQuad> makeBakedQuad(TextureAtlasSprite... texture) {

			List<BakedQuad> ret = new ArrayList<BakedQuad>();

			TextureAtlasSprite side = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/grass_side_overlay");

			Iterator<SlopeModelQuad> itr = this.vertex.iterator();
			int i = 0;
			while(itr.hasNext()) {

				SlopeModelQuad quad = itr.next();
				switch(quad.side) {
				case DOWN:
					i = 0;
					break;
				case UP:
					i = 1;
					break;
				case NORTH:
					i = 2;
					break;
				case EAST:
					i = 3;
					break;
				case SOUTH:
					i = 4;
					break;
				case WEST:
					i = 5;
					break;
				}

				SlopeModelVertex vertex1 = quad.vertex.get(0);
				SlopeModelVertex vertex2 = quad.vertex.get(1);
				SlopeModelVertex vertex3 = quad.vertex.get(2);
				SlopeModelVertex vertex4 = quad.vertex.get(3);

				if(texture[i].getIconName().equals("minecraft:blocks/grass_top")) {

					ret.add(new BakedQuad(Ints.concat(vertex1.vertexToInts(texture[i]), vertex2.vertexToInts(texture[i]), vertex3.vertexToInts(texture[i]), vertex4.vertexToInts(texture[i])), 0, quad.side));
				} else {

					ret.add(new BakedQuad(Ints.concat(vertex1.vertexToInts(texture[i]), vertex2.vertexToInts(texture[i]), vertex3.vertexToInts(texture[i]), vertex4.vertexToInts(texture[i])), -1, quad.side));
				}
				if(texture[i].getIconName().equals("minecraft:blocks/grass_side")) {

					ret.add(new BakedQuad(Ints.concat(vertex1.vertexToInts(side), vertex2.vertexToInts(side), vertex3.vertexToInts(side), vertex4.vertexToInts(side)), 0, quad.side));
				}
			}

			return ret;
		}
	}

}
