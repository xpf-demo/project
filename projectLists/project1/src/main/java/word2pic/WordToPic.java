package word2pic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * 用于将文本中的文字转换到图片上
 * @author xpf_d
 */
public class WordToPic {

	/**
	 * @param text 需要输出到图片的文字
	 * @param widthNum 图片中每行需要展示的文字个数
	 * @param heightNum 图片中需要展示的列数
	 * @param fontSize 文字字体的大小
	 * @return
	 */
	public static String TextToPicNum(String text, int widthNum, int heightNum, int fontSize){
		return TextToPic(text, widthNum*fontSize, heightNum*fontSize, fontSize);
	}

	/**
	 * @param text
	 *            需要输出的文本
	 * @param width
	 *            图片的宽度
	 * @param height
	 *            图片的高度
	 * @param fontSize
	 *            字体的大小
	 * @return
	 */
	public static String TextToPic(String text, int width, int height,int fontSize) {
		try {
			String filepath = "D://pic/" + getDate() + ".png";
			File file = new File(filepath);
			System.out.println("topic=" + text);
			Font font = new Font("微软雅黑", Font.BOLD, fontSize);
			//创建一个图片
			BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			//创建一个画笔，设置画笔
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setBackground(Color.red);
			g2.clearRect(0, 0, width, height);
			g2.setFont(font);
			g2.setPaint(Color.BLACK);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			//在图片上输出内容
			printString(g2,text,width,height,fontSize);
			g2.dispose();
			ImageIO.write(bi, "png", file);
			return "image" + getDate() + ".png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param g2d 画笔
	 * @param str 需要展示的内容
	 * @param width 图片的宽度
	 * @param height 图片的高度
	 * @param fontSize 字体的大小
	 */
	private static void printString(Graphics2D g2d, String str, int width, int height, int fontSize) {
		int x = fontSize;
		int y = fontSize*2;
		//FontMetrics metrics = SwingUtilities2.getFontMetrics(null,g2d.getFont());
		for (char ca : str.toCharArray()) {
			//int px = metrics.stringWidth("" + ca); 获取当前字体的宽度
			g2d.drawString("" + ca, x , y);
			x += fontSize;
			if (ca == '\n' || x >= width-fontSize) {
				x = fontSize;
				y = y + fontSize;
			}
			if(y > height-fontSize){
				return;
			}
		}
	}

	/**
	 * 获取时间
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	public static void main(String[] args) throws IOException {
		String text = "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;夜已深，漆黑一片，景物不可见。但山中并不宁静，猛兽咆哮，震动山河，万木摇颤，乱叶簌簌坠落。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;群山万壑间，洪荒猛兽横行，太古遗种出没，各种可怕的声音在黑暗中此起彼伏，直欲裂开这天地。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;山脉中，远远望去有一团柔和的光隐现，在这黑暗无尽的夜幕下与万山间犹如一点烛火在摇曳，随时会熄灭。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;渐渐接近，可以看清那里有半截巨大的枯木，树干直径足有十几米，通体焦黑。除却半截主干外，它只剩下了一条柔弱的枝条，但却在散发着生机，枝叶晶莹如绿玉刻成，点点柔和的光扩散，将一个村子笼罩。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;确切的说，这是一株雷击木，在很多年前曾经遭遇过通天的闪电，老柳树巨大的树冠与旺盛的生机被摧毁了。如今地表上只剩下**米高的一段树桩，粗的惊人，而那仅有的一条柳枝如绿霞神链般，光晕弥漫，笼罩与守护住了整个村子，令这片栖居地朦朦胧胧，犹若一片仙乡，在这大荒中显得很神秘。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;村中各户都是石屋，夜深人静，这里祥和而安谧，像是与外界的黑暗还有兽吼隔绝了。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;“呜……”<br><br>&nbsp;&nbsp;&nbsp;&nbsp;一阵狂风吹过，一片巨大的乌云横空，遮住了整片夜空，挡住了那仅有的一点星华，山脉中更加黑暗了。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;一声凶戾的禽鸣自高天传来，穿金裂石，竟源自那片乌云，细看它居然是一只庞大到不可思议的巨鸟，遮天蔽月，长也不知多少里。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;路过石村，它俯视下方，两只眼睛宛若两轮血月般，凶气滔天，盯着老柳木看了片刻，最终飞向了山脉最深处。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;平静了很长一段时间，直到后半夜，大地颤动了起来，一条模糊的身影从远方走来，竟与群山齐高！<br><br>&nbsp;&nbsp;&nbsp;&nbsp;莫名气息散发，群山万壑死一般的寂静，凶禽猛兽皆蛰伏，不敢发出一点声音。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;近了，这是一个拥有人形的生物，直立行走，庞大的惊人，身高比肩山岳，浑身没有毛发，通体密布着金色的鳞片，熠熠生辉。面部很平，只有一只竖眼，开合间像是一道金色的闪电划过，犀利慑人。整体血气如海，宛如一尊神魔！<br><br>&nbsp;&nbsp;&nbsp;&nbsp;它路过此地，看了一眼老柳木，稍作停留后，似乎急于赶路，最终快速远去，许多山峰被其脚步震的轰鸣，山地剧烈颤抖。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;黎明，一条十米长、水桶粗、银光灿灿的蜈蚣在山中蜿蜒而行，像是白银浇铸而成，每一节都锃亮而狰狞，划过山石时铿锵作响，火星飞溅。但最终它却避过了石村，没有侵入，所过之处黑雾翻腾，万兽避退。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;一根散发着莹莹绿霞的柔弱柳条在风中轻轻摇曳……<br><br>";
		//TextToPic(text, 20 * 14, 20 * 14, 14);
		TextToPicNum(text, 50, 20, 14);
	}

}