package com.steven.babyiyo.utlis;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

// SDCard工具类
	class SDCardHelper {

		// 判断SD卡是否被挂载
		public static boolean isSDCardMounted() {
			// return Environment.getExternalStorageState().equals("mounted");
			return Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED);
		}

		// 获取SD卡的根目录
		public static String getSDCardBaseDir() {
			if (isSDCardMounted()) {
				return Environment.getExternalStorageDirectory()
						.getAbsolutePath();
			}
			return null;
		}

		// 获取SD卡的完整空间大小，返回MB
		public static long getSDCardSize() {
			if (isSDCardMounted()) {
				StatFs fs = new StatFs(getSDCardBaseDir());
				int count = fs.getBlockCount();
				int size = fs.getBlockSize();
				return count * size / 1024 / 1024;
			}
			return 0;
		}

		// 获取SD卡的剩余空间大小
		public static long getSDCardFreeSize() {
			if (isSDCardMounted()) {
				StatFs fs = new StatFs(getSDCardBaseDir());
				int count = fs.getFreeBlocks();
				int size = fs.getBlockSize();
				return count * size / 1024 / 1024;
			}
			return 0;
		}

		// 获取SD卡的可用空间大小
		public static long getSDCardAvailableSize() {
			if (isSDCardMounted()) {
				StatFs fs = new StatFs(getSDCardBaseDir());
				int count = fs.getAvailableBlocks();
				int size = fs.getBlockSize();
				return count * size / 1024 / 1024;
			}
			return 0;
		}

		// 往SD卡的公有目录下保存文件
		public static boolean saveFileToSDCardPublicDir(byte[] data,
				String type, String fileName) {
			BufferedOutputStream bos = null;
			if (isSDCardMounted()) {
				File file = Environment.getExternalStoragePublicDirectory(type);
				try {
					bos = new BufferedOutputStream(new FileOutputStream(
							new File(file, fileName)));
					bos.write(data);
					bos.flush();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (bos != null) {
							bos.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return false;
		}

		// 往SD卡的自定义目录下保存文件
		public static boolean saveFileToSDCardCustomDir(byte[] data,
				String dir, String fileName) {
			BufferedOutputStream bos = null;
			if (isSDCardMounted()) {
				File file = new File(getSDCardBaseDir() + File.separator + dir);
				if (!file.exists()) {
					file.mkdirs();// 递归创建自定义目录
				}
				try {
					bos = new BufferedOutputStream(new FileOutputStream(
							new File(file, fileName)));
					bos.write(data);
					bos.flush();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return false;
		}

		// 往SD卡的私有Files目录下保存文件
		public static boolean saveFileToSDCardPrivateFilesDir(byte[] data,
				String type, String fileName, Context context) {
			BufferedOutputStream bos = null;
			if (isSDCardMounted()) {
				File file = context.getExternalFilesDir(type);
				try {
					bos = new BufferedOutputStream(new FileOutputStream(
							new File(file, fileName)));
					bos.write(data);
					bos.flush();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return false;
		}

		// 往SD卡的私有Cache目录下保存文件
		public static boolean saveFileToSDCardPrivateCacheDir(byte[] data,
				String fileName, Context context) {
			BufferedOutputStream bos = null;
			if (isSDCardMounted()) {
				File file = context.getExternalCacheDir();
				try {
					bos = new BufferedOutputStream(new FileOutputStream(
							new File(file, fileName)));
					bos.write(data);
					bos.flush();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return false;
		}

		// 保存bitmap图片到SDCard的私有Cache目录
		public static boolean saveBitmapToSDCardPrivateCacheDir(Bitmap bitmap,
				String fileName, Context context) {
			if (isSDCardMounted()) {
				BufferedOutputStream bos = null;
				// 获取私有的Cache缓存目录
				File file = context.getExternalCacheDir();
				try {
					bos = new BufferedOutputStream(new FileOutputStream(
							new File(file, fileName)));
					if (fileName != null
							&& (fileName.contains(".png") || fileName
									.contains(".PNG"))) {
						bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
					} else {
						bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
					}
					bos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bos != null) {
						try {
							bos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return true;
			} else {
				return false;
			}
		}

		// 从SD卡获取文件
		public static byte[] loadFileFromSDCard(String fileDir) {
			BufferedInputStream bis = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			try {
				bis = new BufferedInputStream(new FileInputStream(new File(
						fileDir)));
				byte[] buffer = new byte[8 * 1024];
				int c = 0;
				while ((c = bis.read(buffer)) != -1) {
					baos.write(buffer, 0, c);
					baos.flush();
				}
				return baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (baos != null) {
						baos.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		// 从SDCard中寻找指定目录下的文件，返回Bitmap
		public static Bitmap loadBitmapFromSDCard(String filePath) {
			byte[] data = loadFileFromSDCard(filePath);
			if (data != null) {
				Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
				if (bm != null) {
					return bm;
				}
			}
			return null;
		}

		// 获取SD卡公有目录的路径
		public static String getSDCardPublicDir(String type) {
			return Environment.getExternalStoragePublicDirectory(type)
					.toString();
		}

		// 获取SD卡私有Cache目录的路径
		public static String getSDCardPrivateCacheDir(Context context) {
			return context.getExternalCacheDir().getAbsolutePath();
		}

		// 获取SD卡私有Files目录的路径
		public static String getSDCardPrivateFilesDir(Context context,
				String type) {
			return context.getExternalFilesDir(type).getAbsolutePath();
		}

		public static boolean isFileExist(String filePath) {
			File file = new File(filePath);
			return file.isFile();
		}

		// 从sdcard中删除文件
		public static boolean removeFileFromSDCard(String filePath) {
			File file = new File(filePath);
			if (file.exists()) {
				try {
					file.delete();
					return true;
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	class BitmapThumbnailHelper {
		/**
		 * 对图片进行二次采样，生成缩略图。放置加载过大图片出现内存溢出
		 */
		public static Bitmap createThumbnail(byte[] data, int newWidth,
				int newHeight) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(data, 0, data.length, options);
			int oldWidth = options.outWidth;
			int oldHeight = options.outHeight;

			int ratioWidth = 0;
			int ratioHeight = 0;

			if (newWidth != 0 && newHeight == 0) {
				ratioWidth = oldWidth / newWidth;
				options.inSampleSize = ratioWidth;
			} else if (newWidth == 0 && newHeight != 0) {
				ratioHeight = oldHeight / newHeight;
				options.inSampleSize = ratioHeight;
			}
			options.inPreferredConfig = Config.ALPHA_8;
			options.inJustDecodeBounds = false;
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length,
					options);
			return bm;
		}

		public static Bitmap createThumbnail(String pathName, int newWidth,
				int newHeight) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(pathName, options);
			int oldWidth = options.outWidth;
			int oldHeight = options.outHeight;

			int ratioWidth = 0;
			int ratioHeight = 0;

			if (newWidth != 0 && newHeight == 0) {
				ratioWidth = oldWidth / newWidth;
				options.inSampleSize = ratioWidth;
			} else if (newWidth == 0 && newHeight != 0) {
				ratioHeight = oldHeight / newHeight;
				options.inSampleSize = ratioHeight;
			}
			options.inPreferredConfig = Config.ALPHA_8;
			options.inJustDecodeBounds = false;
			Bitmap bm = BitmapFactory.decodeFile(pathName, options);
			return bm;
		}
	

	public String getImageName(String url) {
		String imageName = "";
		if (url != null) {
			imageName = url.substring(url.lastIndexOf("/") + 1);
		}
		return imageName;
	}
	}
