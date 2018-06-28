package com.alcohol.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
	private static String basePath = Thread.currentThread()
			.getContextClassLoader().getResource("").getPath();

	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static final Random r = new Random();

	/**
	 * 处理缩略图
	 * 
	 * @param thumb
	 * @return
	 */
	public static String generateThumbnail(CommonsMultipartFile thumbnail,
			String targetAddr) {
		String realFileName = getRandomFileName();// 随机名
		String extension = getFileExtension(thumbnail);// 获取上传的扩展名
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;// 新名称路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);// 新路径
		// 创建缩略图
		try {
			Thumbnails
					.of(thumbnail.getInputStream())
					.size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File(basePath + "/suo.jpg")),
							0.25f).outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return realFileName;
	}

	/**
	 * 创建目标路径所涉及的目录
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		String realFilePath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFilePath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param cFile
	 * @return
	 */
	private static String getFileExtension(CommonsMultipartFile cFile) {
		// TODO Auto-generated method stub
		String originalFileName = cFile.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日分钟秒+5位随机数
	 * 
	 * @return
	 */
	private static String getRandomFileName() {
		// TODO Auto-generated method stub
		// 获取随机5位置
		int reNum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return reNum + nowTimeStr;
	}

	public static void main(String[] args) throws IOException {

		Thumbnails
				.of(new File("D:/beidaqingniao/image/faq03.jpg"))
				.size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT,
						ImageIO.read(new File(basePath + "/suo.jpg")), 0.25f)
				.outputQuality(0.8f)
				.toFile("D:/beidaqingniao/image/faq03new.jpg");
	}
}
