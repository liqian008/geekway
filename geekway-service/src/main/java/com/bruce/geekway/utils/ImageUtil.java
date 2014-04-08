package com.bruce.geekway.utils;

import java.io.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * 图像切割（改） *
	 * 
	 * @param srcImageFile源图像地址
	 * @param dirImageFile新图像地址
	 * @param x目标切片起点x坐标
	 * @param y目标切片起点y坐标
	 * @param destWidth目标切片宽度
	 * @param destHeight目标切片高度
	 */
	public static void abscut(String srcImageFile, String dirImageFile, int x,
			int y, int destWidth, int destHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (srcWidth >= destWidth && srcHeight >= destHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 改进的想法:是否可用多线程加快切割速度
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
				img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(destWidth, destHeight,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null); // 绘制缩小后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", new File(dirImageFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩放图像
	 * 
	 * @param srcImageFile源图像文件地址
	 * @param result缩放后的图像地址
	 * @param scale缩放比例
	 * @param flag缩放选择:true 放大; false 缩小;
	 */
	public static void scale(String srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {
				// 放大
				width = width * scale;
				height = height * scale;
			} else {
				// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重新生成按指定宽度和高度的图像
	 * 
	 * @param srcImageFile源图像文件地址
	 * @param result新的图像地址
	 * @param _width设置新的图像宽度
	 * @param _height设置新的图像高度
	 * @throws IOException 
	 */
	public static void scale(String srcImageFile, String result, int _width,
			int _height) throws IOException {
		scale(srcImageFile, result, _width, _height, 0, 0);
	}
	
	/**
	 * 重新生成按指定宽度图像
	 * @param srcImageFile
	 * @param result
	 * @param _width
	 * @param _height
	 * @throws IOException 
	 */
    public static void scaleByWidth(String srcImageFile, String result, int _width) throws IOException {
        scale(srcImageFile, result, _width, 0);
    }
	
	public static void scale(String srcImageFile, String targetImageFile, int _width,
			int _height, int x, int y) throws IOException {
		scale(new File(srcImageFile), new File(targetImageFile), _width,
	            _height, x, y);
	}
	
	public static void scale(File sourceImage, File targetImage, int _width,
            int _height, int x, int y) throws IOException {
            BufferedImage src = ImageIO.read(sourceImage); // 读入文件
            int sourceWidth = src.getWidth(); // 得到源图宽
            int sourceHeight = src.getHeight(); // 得到源图长
            int width = sourceWidth;
            int height = sourceHeight;
            
            if(_height<=0){//忽略height，按width进行缩放
                _height = (int)Math.round(( sourceHeight * _width * 1.0 / sourceWidth));
            }
            
            if (sourceWidth > _width) {
                width = _width;
            }
            if (sourceHeight > _height) {
                height = _height;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, x, y, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", targetImage);// 输出到文件流
       
    }
	
	/**
	 * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
	 */
	public static void convert(String source, String result) {
		try {
			File f = new File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 彩色转为黑白
	 * 
	 * @param source
	 * @param result
	 */
	public static void gray(String source, String result) {
		try {
			BufferedImage src = ImageIO.read(new File(source));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 缩放图片
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] zoom(byte[] data, int width, int height) {
        try {
            BufferedImage source = ImageIO.read(new ByteArrayInputStream(data));
            // targetW，targetH分别表示目标长和宽  
            int type = source.getType();
            BufferedImage target = null;
            double sx = (double) width / source.getWidth();
            double sy = (double) height / source.getHeight();
            //这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放  
            //则将下面的if else语句注释即可  
            if (sx > sy) {
                sx = sy;
                width = (int) (sx * source.getWidth());
            } else {
                sy = sx;
                height = (int) (sy * source.getHeight());
            }
            if (type == BufferedImage.TYPE_CUSTOM) {//handmade  
                ColorModel cm = source.getColorModel();
                WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
                boolean alphaPremultiplied = cm.isAlphaPremultiplied();
                target = new BufferedImage(cm, raster, alphaPremultiplied, null);
            } else {
                target = new BufferedImage(width, height, type);
            }
            Graphics2D g = target.createGraphics();
            //smoother than exlax:  
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
            g.dispose();
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(target, "jpg", bos);
            return bos.toByteArray();
        } catch (Exception e) {
            //logger.error(e);
            return null;
        }
    }

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// cut("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg", 200, 150);
		// ok
		// gray("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg");
		// convert("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.gif");
		// scale("c:/images/5105049910001020110718648723.jpg",
		// "c:/images/t/5105049910001020110718648725.jpg",154,166,157,208);
		// scale("c:/images/rose1.jpg",
		// "c:/images/t/rose1.jpg",154,166,157,208);
		scale("/home/liqian/Desktop/pic/panoramic-870x450.jpg", "/home/liqian/Desktop/pic/temp.jpg", 200, 0);

	}
}