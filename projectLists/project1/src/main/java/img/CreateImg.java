package img;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * @author madison
 *
 */
public class CreateImg {
	/*public static void main(String[] args) throws Exception {
		//获取当前的项目路径
        String address = CreateImg.class.getResource("/").getPath();
        // 头像
        String avastar = address + "png/avastar.jpg";
        // 二维码
        String qrcode = address + "png/qrcode.jpg";
        // 二维码识别图
        String point = address + "png/point.png";
        // 背景图片
        String background = address + "png/background.jpg";

        // 1. 通过背景图片构建 BufferedImage
        BufferedImage zoomPicture = ImageIO.read(new File(background));
        // 2. 头像裁剪成圆形
        BufferedImage roundedImage = SharedImageUtils.createRoundedImage(avastar, SharedImageUtils.AVATAR_SIZE);
        // 3.1 合并头像，昵称
        BufferedImage mergeImage = SharedImageUtils.mergePicture(zoomPicture, roundedImage, "一起来玩呀");
        // 4. 合并二维码及二维码识别图
        mergeImage = SharedImageUtils.mergeQrcode(mergeImage, qrcode, point);
        // 5. 生成分享图
        ImageIO.write(mergeImage, "jpg", new File(address + "png/result.jpg"));
	}*/
	
	/*public static void main(String[] args) throws Exception{
		//获取当前的项目路径
        String address = CreateImg.class.getResource("/").getPath();
        // 头像
        String avastar = address + "png/avastar.jpg";
        // 二维码
        String qrcode = address + "png/qrcode.jpg";
        // 背景图片
        String background = address + "png/background.jpg";

        // 1. 通过背景图片构建 BufferedImage
        BufferedImage zoomPicture = ImageIO.read(new File(background));
        // 2. 头像裁剪成圆形
        BufferedImage roundedImage = SharedImageUtils.createRoundedImage(avastar, SharedImageUtils.AVATAR_SIZE);
        Param param = new Param();
        // 3 合并头像，昵称，二维码图片，二维码描述
        BufferedImage mergeImage = SharedImageUtils.mergePic(zoomPicture, roundedImage, param.getNickName(), ImageIO.read(new File(qrcode)),param);
        // 5. 生成分享图
        ImageIO.write(mergeImage, "jpg", new File(address + "png/result.jpg"));
	}*/
	
	public static void createPic(Param param) throws Exception{
		 // 1. 通过背景图片构建 BufferedImage
        BufferedImage zoomPicture = ImageIO.read(new File(param.getBackgroundUrl()));
        // 2. 头像裁剪成圆形
        BufferedImage roundedImage = SharedImageUtils.createRoundedImage(param.getHeadPicUrl(), SharedImageUtils.AVATAR_SIZE);
        // 3 合并头像，昵称，二维码图片，二维码描述
        BufferedImage mergeImage = SharedImageUtils.mergePic(zoomPicture, roundedImage,param);
	}
	
	

}
