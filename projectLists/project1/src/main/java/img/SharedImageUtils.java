package img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 生成朋友圈分享图相关接口 用于测试，不用太过于关注代码规范
 */
public class SharedImageUtils {

	/* 要放置的头像半径 */
	public static final int AVATAR_SIZE = 160;

	/**
	 * 裁剪图片
	 *
	 * @param img          the img
	 * @param originWidth  the origin width
	 * @param originHeight the origin height
	 * @return the buffered image
	 * @throws Exception the exception
	 */
	public static BufferedImage cutPicture(BufferedImage img, int originWidth, int originHeight) throws IOException {
		int width = img.getWidth(); // 原图的宽度
		int height = img.getHeight(); // 原图的高度

		int newImage_x = 0; // 要截图的坐标
		int newImage_y = 0; // 要截图的坐标
		if (width > originWidth) {
			newImage_x = (width - originWidth) / 2;
		}
		if (height > originHeight) {
			newImage_y = height - originHeight;
		}
		return cutJPG(img, newImage_x, newImage_y, originWidth, originHeight);
	}

	/**
	 * 进行裁剪操作
	 *
	 * @param originalImage the original image
	 * @param x             the x
	 * @param y             the y
	 * @param width         the width
	 * @param height        the height
	 * @return the buffered image
	 * @throws IOException the io exception
	 */
	public static BufferedImage cutJPG(BufferedImage originalImage, int x, int y, int width, int height)
			throws IOException {
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = iterator.next();
		// 转换成字节流
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", outputStream);
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

		ImageInputStream iis = ImageIO.createImageInputStream(is);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		return bi;
	}

	/**
	 * 画图
	 * 
	 * @param baseImage
	 * @param topImage
	 * @param nickName
	 * @param qrCodeImage
	 * @param qrCodeContext
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage mergePic(BufferedImage baseImage, BufferedImage topImage, Param param)
			throws IOException {
		int width = baseImage.getWidth(null); // 获取背景底图的宽度
		int height = baseImage.getHeight(null); // 获取背景底图的高度
		// 按照底图的宽高生成新的图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.drawImage(baseImage, 0, 0, width, height, null);
		// 先画最下面的文字描述
		// 普通字体
		Font font = new Font("微软雅黑", Font.PLAIN, 25);
		g.setFont(font);
		g.setColor(new Color(255, 255, 255));
		FontMetrics fm = g.getFontMetrics(font);
		// 画文本 昵称长度和放置的位置
		int textHeight = fm.getHeight();
		// 1.画第一个文本
		g.drawString(param.getNickName(), 54, height - textHeight);
		// 画图
		// 设置上层图片放置的位置的坐标及大小
		g.drawImage(topImage, 54, height - 175, 100, 100, null);
		g.drawImage(ImageIO.read(new File(param.getQrCodeUrl())), width - 180, height - 220, 150, 150, null);

		// 贴汉字
		g.drawString("你的朋友圈被访问了", 400, 440);
		g.drawString("次", 550, 480);
		g.drawString("抢了", 90, 550);
		g.drawString("个红包", 210, 550);
		g.drawString("手气最佳", 100, 590);
		g.drawString("次", 270, 590);
		g.drawString("最多一天发送表情", 450, 720);
		g.drawString("次", 600, 760);
		g.drawString("支付共", 120, 850);
		g.drawString("次", 270, 850);
		g.drawString("最喜欢用的一个词语", 400, 980);
		g.drawString("你的朋友圈访问次数排", 180, 1200);
		g.drawString("到全网前", 180, 1230);
		g.drawString(param.getRankingOfVisitsFriends(), 280, 1230);

		// 贴数字
		font = new Font("微软雅黑", Font.PLAIN, 40);
		g.setFont(font);
		g.setColor(new Color(255, 255, 255));
		g.drawString(param.getTimesOfVisitsFriends(), 460, 485);
		g.drawString(param.getTimesOfRedPacket(), 140, 553);
		g.drawString(param.getTimesOfprobability(), 200, 595);
		g.drawString(param.getTimesOfLook(), 530, 765);
		g.drawString(param.getTimesOfPay(), 195, 855);
		g.drawString(param.getTerms(), 450, 1030);
		g.dispose();
		return image;
	}

	/**
	 * 方形转为圆形
	 *
	 * @param img    the img
	 * @param radius the radius 半径
	 * @return the buffered image
	 * @throws Exception the exception
	 */
	public static BufferedImage convertRoundedImage(BufferedImage img, int radius) throws IOException {
		BufferedImage result = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		// 在适当的位置画图
		g.drawImage(img, (radius - img.getWidth(null)) / 2, (radius - img.getHeight(null)) / 2, null);

		// 圆角
		RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, radius, radius, radius * 2, radius * 2);
		Area clear = new Area(new Rectangle(0, 0, radius, radius));
		clear.subtract(new Area(round));
		g.setComposite(AlphaComposite.Clear);

		// 抗锯齿
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fill(clear);
		g.dispose();

		return result;
	}

	/**
	 * 图像等比例缩放
	 *
	 * @param img     the img
	 * @param maxSize the max size
	 * @param type    the type
	 * @return the scaled image
	 */
	private static BufferedImage getScaledImage(BufferedImage img, int maxSize, int type) {
		int w0 = img.getWidth();
		int h0 = img.getHeight();
		int w = w0;
		int h = h0;
		// 头像如果是长方形：
		// 1:高度与宽度的最大值为maxSize进行等比缩放,
		// 2:高度与宽度的最小值为maxSize进行等比缩放
		if (type == 1) {
			w = w0 > h0 ? maxSize : (maxSize * w0 / h0);
			h = w0 > h0 ? (maxSize * h0 / w0) : maxSize;
		} else if (type == 2) {
			w = w0 > h0 ? (maxSize * w0 / h0) : maxSize;
			h = w0 > h0 ? maxSize : (maxSize * h0 / w0);
		}
		Image schedImage = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(schedImage, 0, 0, null);
		return bufferedImage;
	}

	/**
	 * 对头像处理
	 *
	 * @param image  the image
	 * @param radius the radius
	 * @return the buffered image
	 * @throws Exception the exception
	 */
	public static BufferedImage createRoundedImage(String image, int radius) throws Exception {
		BufferedImage img = ImageIO.read(new File(image));
		// 1. 按原比例缩减
		BufferedImage fixedImg = getScaledImage(img, radius, 2);
		// 2. 居中裁剪
		fixedImg = cutPicture(fixedImg, radius, radius);
		// 3. 把正方形生成圆形
		BufferedImage bufferedImage = convertRoundedImage(fixedImg, radius);
		return bufferedImage;
	}

	/**
	 * 创建图片
	 * 
	 * @param param
	 * @throws Exception
	 */
	public static void createPic(Param param) throws Exception {

		// 1. 通过背景图片构建 BufferedImage
		BufferedImage zoomPicture = ImageIO.read(new File(param.getBackgroundUrl()));
		// 2. 头像裁剪成圆形
		BufferedImage roundedImage = createRoundedImage(param.getHeadPicUrl(), SharedImageUtils.AVATAR_SIZE);
		// 3 合并头像，昵称，二维码图片，二维码描述
		BufferedImage mergeImage = mergePic(zoomPicture, roundedImage, param);
		// 5. 生成分享图
		ImageIO.write(mergeImage, "jpg", new File(SharedImageUtils.class.getResource("/").getPath() + "png/result.jpg"));
	}

	public static void main(String[] args) throws Exception {
		// 获取当前的项目路径
		String address = SharedImageUtils.class.getResource("/").getPath();
		Param param = new Param();
		param.setBackgroundUrl(address + "png/background.jpg");
		param.setHeadPicUrl(address + "png/avastar.jpg");
		param.setQrCodeUrl(address + "png/qrcode.jpg");
		param.setNickName("炫小峰");
		param.setRankingOfVisitsFriends(RandomUtils.rankingOfVisitsFriends());
		param.setTerms(RandomUtils.getTerms());
		param.setTimesOfLook(RandomUtils.timesOfLook());
		param.setTimesOfPay(RandomUtils.timesOfPay());
		param.setTimesOfRedPacket(RandomUtils.timesOfRedPacket());
		param.setTimesOfprobability(RandomUtils.timesOfOptimal(Integer.parseInt(param.getTimesOfRedPacket())));
		param.setTimesOfVisitsFriends(RandomUtils.timesOfVisitsFriends());
		createPic(param);
	}

}