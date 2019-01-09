package demo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Image_Processing {

	int[] chartGrayscale(BufferedImage image) throws IOException {
		image = grayImage(image);
		int[] arr = new int[255];
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int number = color.getRed();
				arr[number]++;
			}
		}
		return arr;
	}

	BufferedImage grayImage(BufferedImage image) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int red = (int) (color.getRed() * 0.299);
				int green = (int) (color.getGreen() * 0.587);
				int blue = (int) (color.getBlue() * 0.114);
				Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage negativeImage(BufferedImage image) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				Color newColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage negativeGrayImage(BufferedImage image) throws IOException {
		image = grayImage(image);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				Color newColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage binaryImage(BufferedImage image, int threshold) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int x = (color.getRed() > threshold) ? 255 : 0;
				int y = (color.getGreen() > threshold) ? 255 : 0;
				int z = (color.getBlue() > threshold) ? 255 : 0;
				Color newColor = new Color(x, y, z);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage logaritImage(BufferedImage image, int c) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int red = c * (int) Math.log(color.getRed() + 1);
				int green = c * (int) Math.log(color.getGreen() + 1);
				int blue = c * (int) Math.log(color.getBlue() + 1);
				Color newColor = new Color(red, green, blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage power_lawImage(BufferedImage image, int gamma, int c) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int red = (int) (Math.pow((color.getRed() / 255.), gamma) * c);
				int green = (int) (Math.pow((color.getGreen() / 255.), gamma) * c);
				int blue = (int) (Math.pow((color.getBlue() / 255.), gamma) * c);
				Color newColor = new Color(red, green, blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage laplacianImage(BufferedImage image) throws IOException {
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				Color color = new Color(image.getRGB(i, j));
				Color color_Top = new Color(image.getRGB(i, j - 1));
				Color color_Bottom = new Color(image.getRGB(i, j + 1));
				Color color_Left = new Color(image.getRGB(i - 1, j));
				Color color_Right = new Color(image.getRGB(i + 1, j));
				int red = 5 * color.getRed() - color_Top.getRed() - color_Bottom.getRed() - color_Left.getRed()
						- color_Right.getRed();
				int green = 5 * color.getGreen() - color_Top.getGreen() - color_Bottom.getGreen()
						- color_Left.getGreen() - color_Right.getGreen();
				int blue = 5 * color.getBlue() - color_Top.getBlue() - color_Bottom.getBlue() - color_Left.getBlue()
						- color_Right.getBlue();
				red = (red < 0) ? 0 : red;
				red = (red > 255) ? 255 : red;
				green = (green < 0) ? 0 : green;
				green = (green > 255) ? 255 : green;
				blue = (blue < 0) ? 0 : blue;
				blue = (blue > 255) ? 255 : blue;
				Color newColor = new Color(red, green, blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage laplacianExtensionImage(BufferedImage image) throws IOException {
		Color[] color = new Color[9];
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color = getMatrixColor(image, i, j);
				int red = 9 * color[4].getRed() - color[0].getRed() - color[1].getRed() - color[2].getRed()
						- color[3].getRed() - color[5].getRed() - color[6].getRed() - color[7].getRed()
						- color[8].getRed();
				int green = 9 * color[4].getGreen() - color[0].getGreen() - color[1].getGreen() - color[2].getGreen()
						- color[3].getGreen() - color[5].getGreen() - color[6].getGreen() - color[7].getGreen()
						- color[8].getGreen();
				int blue = 9 * color[4].getBlue() - color[0].getBlue() - color[1].getBlue() - color[2].getBlue()
						- color[3].getBlue() - color[5].getBlue() - color[6].getBlue() - color[7].getBlue()
						- color[8].getBlue();
				red = (red < 0) ? 0 : red;
				red = (red > 255) ? 255 : red;
				green = (green < 0) ? 0 : green;
				green = (green > 255) ? 255 : green;
				blue = (blue < 0) ? 0 : blue;
				blue = (blue > 255) ? 255 : blue;
				// System.out.println(i + " " + j + ": " + red + " " + green + " " + blue);
				Color newColor = new Color(red, green, blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage bitPlaneSlicing(BufferedImage image, int constant) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int red = color.getRed() | (int) (Math.pow(2, constant));
				int green = color.getGreen() | (int) (Math.pow(2, constant));
				int blue = color.getBlue() | (int) (Math.pow(2, constant));
				Color newColor = new Color(red, green, blue);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage minNeighbourhoodImage(BufferedImage image) throws IOException {
		BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		image_origin.setData(image.getData());
		Color[] color = new Color[9];
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color = getMatrixColor(image_origin, i, j);
				int[] sum = new int[9];
				for (int u = 0; u < 9; u++) {
					sum[u] = sum_Color(color[u]);
				}
				Color newColor = color[min_color(sum)];
				image.setRGB(i, j, newColor.getRGB());
			}
		}

		return image;
	}

	BufferedImage maxNeighbourhoodImage(BufferedImage image) throws IOException {
		BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		image_origin.setData(image.getData());
		Color[] color = new Color[9];
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color = getMatrixColor(image_origin, i, j);
				int[] sum = new int[9];
				for (int u = 0; u < 9; u++) {
					sum[u] = sum_Color(color[u]);
				}
				Color newColor = color[max_color(sum)];
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage medianImage(BufferedImage image) throws IOException {
		BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		image_origin.setData(image.getData());
		Color[] color = new Color[9];
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color = getMatrixColor(image_origin, i, j);
				int[] sum = new int[9];
				for (int u = 0; u < 9; u++) {
					sum[u] = sum_Color(color[u]);
				}
				image.setRGB(i, j, medianColor(color, sum).getRGB());
			}
		}
		return image;
	}

	BufferedImage smoothingImage(BufferedImage image) throws IOException {
		BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		image_origin.setData(image.getData());
		Color[] color = new Color[9];
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color = getMatrixColor(image_origin, i, j);
				int smoothing_Red = 0;
				int smoothing_Green = 0;
				int smoothing_Blue = 0;
				for (int u = 0; u < 9; u++) {
					smoothing_Red += color[u].getRed();
					smoothing_Green += color[u].getGreen();
					smoothing_Blue += color[u].getBlue();
				}
				Color newColor = new Color(smoothing_Red / 9, smoothing_Green / 9, smoothing_Blue / 9);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage weightSmoothingImage(BufferedImage image) throws IOException {
		BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		image_origin.setData(image.getData());
		Color[] color = new Color[9];
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color = getMatrixColor(image_origin, i, j);
				int weight_Smoothing_Red = 0;
				int weight_Smoothing_Green = 0;
				int weight_Smoothing_Blue = 0;
				int heso;
				for (int u = 0; u < 9; u++) {
					if (u == 4) { // Lấy vị trí trung tâm ma trận 3x3
						heso = 4;
					} else if (u % 2 == 0) { // Các vị trí 0 2 6 8
						heso = 1;
					} else { // Các vị trí 1 3 5 7
						heso = 2;
					}
					weight_Smoothing_Red += color[u].getRed() * heso;
					weight_Smoothing_Green += color[u].getGreen() * heso;
					weight_Smoothing_Blue += color[u].getBlue() * heso;
				}
				Color newColor = new Color(weight_Smoothing_Red / 16, weight_Smoothing_Green / 16,
						weight_Smoothing_Blue / 16);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage gradientImage(BufferedImage image) throws IOException {
		for (int i = 0; i < image.getWidth() - 1; i++) {
			for (int j = 0; j < image.getHeight() - 1; j++) {
				Color color = new Color(image.getRGB(i, j));
				Color row_color = new Color(image.getRGB(i + 1, j));
				Color col_color = new Color(image.getRGB(i, j + 1));
				int red = color.getRed() * (-1) + row_color.getRed() * 1 + color.getRed() * (-1)
						+ col_color.getRed() * (1);
				int green = color.getGreen() * (-1) + row_color.getGreen() * 1 + color.getGreen() * (-1)
						+ col_color.getGreen() * (1);
				int blue = color.getBlue() * (-1) + row_color.getBlue() * 1 + color.getBlue() * (-1)
						+ col_color.getBlue() * (1);
				if (Math.abs(red) > 255 || Math.abs(green) > 255 || Math.abs(blue) > 255) {
					red = 255;
					green = 255;
					blue = 255;
				}
				Color newColor = new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage sobelImage(BufferedImage image) throws IOException {
		for (int i = 0; i < image.getWidth() - 2; i++) {
			for (int j = 0; j < image.getHeight() - 2; j++) {
				image.setRGB(i, j, getColorSobel(image, i, j).getRGB());
			}
		}
		return image;
	}

	BufferedImage prewittImage(BufferedImage image) throws IOException {
		for (int i = 0; i < image.getWidth() - 2; i++) {
			for (int j = 0; j < image.getHeight() - 2; j++) {
				image.setRGB(i, j, getColorPrewitt(image, i, j).getRGB());
			}
		}
		return image;
	}

	BufferedImage frequencyBalancingImage(BufferedImage image, float new_lever) throws IOException {
		image = grayImage(image);
		int[] arr_G = chartGrayscale(image); // Mảng biểu đồ mức xám
		float TB = (image.getWidth() * image.getHeight()) / new_lever;
		int[] arr_hG = new int[arr_G.length];
		int[] arr_fG = new int[arr_G.length];

		for (int i = 0; i < arr_G.length; i++) {
			if (arr_G[i] == 0)
				continue;
			int sum = 0;
			for (int j = 0; j < i; j++) {
				sum += arr_G[j];
			}
			arr_hG[i] = sum;
			arr_fG[i] = (int) Math.max(0, Math.round(arr_hG[i] / TB) - 1);
		}

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				int number = color.getRed();
				Color newColor = new Color(arr_fG[number], arr_fG[number], arr_fG[number]);
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	BufferedImage colourImage(BufferedImage image, float h, float s, float b) throws IOException {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
				float hue = hsb[0];
				float saturation = hsb[1];
				float brightness = hsb[2];
				hue += h;
				saturation += s;
				brightness += b;

				hue = (hue > 1) ? 1 : hue;
				saturation = (saturation > 1) ? 1 : saturation;
				brightness = (brightness > 1) ? 1 : brightness;

				hue = (hue < 0) ? 0 : hue;
				saturation = (saturation < 0) ? 0 : saturation;
				brightness = (brightness < 0) ? 0 : brightness;

				Color newColor = new Color(Color.HSBtoRGB(hue, saturation, brightness));
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		return image;
	}

	Color medianColor(Color[] color, int[] sum) {
		int temp_sum;
		Color temp_Color = null;
		for (int i = 0; i < sum.length; i++) {
			for (int j = sum.length - 1; j > i; j--) {
				if (sum[j] < sum[j - 1]) {
					temp_sum = sum[j - 1];
					sum[j - 1] = sum[j];
					sum[j] = temp_sum;
					temp_Color = color[j - 1];
					color[j - 1] = color[j];
					color[j] = temp_Color;
				}
			}
		}
		return color[4];
	}

	int min_color(int[] sum) {
		int position = 0;
		int min = 255;
		for (int i = 0; i < sum.length; i++) {
			if (sum[i] < min) {
				position = i;
				min = sum[i];
			}
		}
		return position;
	}

	int max_color(int[] sum) {
		int position = 0;
		int max = 0;
		for (int i = 0; i < sum.length; i++) {
			if (sum[i] > max) {
				position = i;
				max = sum[i];
			}
		}
		return position;
	}

	int sum_Color(Color color) {
		return color.getRed() + color.getGreen() + color.getBlue();
	}

	Color[] getMatrixColor(BufferedImage image, int i, int j) {
		Color[] color = new Color[9];
		color[0] = new Color(image.getRGB(i - 1, j - 1));
		color[1] = new Color(image.getRGB(i, j - 1));
		color[2] = new Color(image.getRGB(i + 1, j - 1));
		color[3] = new Color(image.getRGB(i - 1, j));
		color[4] = new Color(image.getRGB(i, j));
		color[5] = new Color(image.getRGB(i + 1, j));
		color[6] = new Color(image.getRGB(i - 1, j + 1));
		color[7] = new Color(image.getRGB(i, j + 1));
		color[8] = new Color(image.getRGB(i + 1, j + 1));
		return color;
	}

	Color getColorSobel(BufferedImage image, int i, int j) {
		Color[][] color = new Color[3][3];
		int[][] hX = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
		int[][] hY = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int u = 0; u < 3; u++) {
			for (int v = 0; v < 3; v++) {
				color[u][v] = new Color(image.getRGB(u + i, v + j));
				red += color[u][v].getRed() * (hX[u][v]) + color[u][v].getRed() * (hY[u][v]);
				green += color[u][v].getGreen() * (hX[u][v]) + color[u][v].getGreen() * (hY[u][v]);
				blue += color[u][v].getBlue() * (hX[u][v]) + color[u][v].getBlue() * (hY[u][v]);
			}
		}
		if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
			red = 255;
			green = 255;
			blue = 255;
		}
		return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
	}

	Color getColorPrewitt(BufferedImage image, int i, int j) {
		Color[][] color = new Color[3][3];
		int[][] hX = { { -1, 0, 1 }, { -1, 0, 1 }, { -1, 0, 1 } };
		int[][] hY = { { -1, -1, -1 }, { 0, 0, 0 }, { 1, 1, 1 } };
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int u = 0; u < 3; u++) {
			for (int v = 0; v < 3; v++) {
				color[u][v] = new Color(image.getRGB(u + i, v + j));
				red += color[u][v].getRed() * (hX[u][v]) + color[u][v].getRed() * (hY[u][v]);
				green += color[u][v].getGreen() * (hX[u][v]) + color[u][v].getGreen() * (hY[u][v]);
				blue += color[u][v].getBlue() * (hX[u][v]) + color[u][v].getBlue() * (hY[u][v]);
			}
		}
		if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
			red = 255;
			green = 255;
			blue = 255;
		}
		return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
	}

	void save_Image(BufferedImage image, String nameImage, String typeImage) throws IOException {
		ImageIO.write(image, typeImage, new File(nameImage + '.' + typeImage));
		JOptionPane.showMessageDialog(null, "Lưu file thành công", "Lưu File", JOptionPane.INFORMATION_MESSAGE);
	}

}
