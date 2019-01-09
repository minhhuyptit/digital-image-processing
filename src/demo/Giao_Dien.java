package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsDevice.WindowTranslucency;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.event.ChangeEvent;

public class Giao_Dien extends JFrame {

	/** 
	 * NGUYEN HUY
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_path;
	private JTextField tf_c;
	private JTextField tf_gamma;
	File file = null;
	File file_save = null;
	Image_Processing ip = null;
	BufferedImage img_goc = null;
	BufferedImage img = null;
	BufferedImage image = null;
	JButton bt_save = new JButton(new ImageIcon("save.png"));
	private JTextField tf_c_power;
	private JTextField tf_bit;
	private JRadioButton rd_1;
	private JRadioButton rd_2;
	private JRadioButton rd_3;
	private JRadioButton rd_4;
	private JRadioButton rd_5;
	private JRadioButton rd_6;
	private JRadioButton rd_7;
	private JRadioButton rd_8;
	private JRadioButton rd_9;
	private JRadioButton rd_10;
	private JRadioButton rd_11;
	private JRadioButton rd_13;
	private JRadioButton rd_12;
	private JRadioButton rd_14;
	private JRadioButton rd_16;
	private JRadioButton rd_17;
	private JRadioButton rd_15;
	private JRadioButton rd_18;
	private JLabel lb_image;
	private JRadioButton rd_19;
	private JRadioButton rd_20;
	private JLabel lbHue;
	private JLabel lbSaturation;
	private JLabel lbBrightness;
	private JSlider slider_hue;
	private JSlider slider_saturation;
	private JSlider slider_brightness;
	private JLabel lb_h;
	private JLabel lb_s;
	private JLabel lb_b;
	private JButton btnConvert;
	private JLabel lb_newLever;
	private JSlider sliderNewLever;
	private JSlider slider_Threshold;
	private JLabel lb_th;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Giao_Dien frame = new Giao_Dien();
					frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void paint(Graphics g) {
		super.paint(g);
		if (file != null && rd_18.isSelected()) {
			lb_image.setIcon(null);
			int[] arr = new int[255];
			try {
				image.setData(img_goc.getData());
				arr = ip.chartGrayscale(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setColor(Color.WHITE);
			g.fillRect(850+8, 65+31, 400, 600);
			g.setColor(Color.BLACK);
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] == 0) continue;
				int x = arr[i] % 590;
				g.drawLine(920 + i, 690, 920 + i, 690 - x);
			}
		}

	}

	public Giao_Dien() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(5, 5, 1280, 725);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.setTitle("Bài tập Xử Lý Ảnh");
		this.setIconImage(new ImageIcon("icon.png").getImage());
//		this.setSize, height);
		contentPane.setLayout(null);

		tf_path = new JTextField();
		tf_path.setEnabled(false);
		tf_path.setBounds(29, 8, 238, 31);
		contentPane.add(tf_path);
		tf_path.setColumns(10);

		JLabel lb_threshold = new JLabel("|  Threshold:");
		lb_threshold.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_threshold.setBounds(133, 175, 83, 23);
		contentPane.add(lb_threshold);

		rd_1 = new JRadioButton("Gray Image");
		rd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_1.setBounds(27, 94, 109, 23);
		contentPane.add(rd_1);

		rd_2 = new JRadioButton("Negative Image");
		rd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_2.setBounds(27, 121, 120, 23);
		contentPane.add(rd_2);

		rd_3 = new JRadioButton("Negative Gray Image");
		rd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_3.setBounds(27, 148, 155, 23);
		contentPane.add(rd_3);

		rd_4 = new JRadioButton("Binary Image");
		rd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
				if (file != null) {
					slider_Threshold.setEnabled(true);
					lb_th.setEnabled(true);
				}
			}
		});
		rd_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_4.setBounds(27, 175, 100, 23);
		contentPane.add(rd_4);

		rd_5 = new JRadioButton("Logarit Image");
		rd_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
				tf_c.setEnabled(true);
			}
		});
		rd_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_5.setBounds(27, 202, 104, 23);
		contentPane.add(rd_5);

		rd_6 = new JRadioButton("Pow-law Image");
		rd_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
				tf_gamma.setEnabled(true);
				tf_c_power.setEnabled(true);
			}
		});
		rd_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_6.setBounds(27, 229, 115, 23);
		contentPane.add(rd_6);

		rd_7 = new JRadioButton("Laplacian Image");
		rd_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_7.setBounds(27, 256, 120, 23);
		contentPane.add(rd_7);

		rd_8 = new JRadioButton("Laplacian Extension Image");
		rd_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_8.setBounds(27, 283, 180, 23);
		contentPane.add(rd_8);

		rd_9 = new JRadioButton("Bit Plane Sciling");
		rd_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
				tf_bit.setEnabled(true);
			}
		});
		rd_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_9.setBounds(27, 310, 120, 23);
		contentPane.add(rd_9);

		rd_10 = new JRadioButton("Min Neighbourhood Image");
		rd_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_10.setBounds(27, 337, 184, 23);
		contentPane.add(rd_10);

		rd_11 = new JRadioButton("Max Neighbourhood Image");
		rd_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_11.setBounds(27, 364, 183, 23);
		contentPane.add(rd_11);

		rd_12 = new JRadioButton("Smoothing Image");
		rd_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_12.setBounds(27, 418, 150, 23);
		contentPane.add(rd_12);

		rd_13 = new JRadioButton("Median Image");
		rd_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_13.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_13.setBounds(27, 391, 109, 23);
		contentPane.add(rd_13);

		rd_14 = new JRadioButton("Weight Smoothing Image");
		rd_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_14.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_14.setBounds(27, 445, 189, 23);
		contentPane.add(rd_14);

		rd_15 = new JRadioButton("Phát hiện biên Gradient");
		rd_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_15.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_15.setBounds(27, 472, 161, 23);
		contentPane.add(rd_15);

		rd_16 = new JRadioButton("Phát hiện biên Sobel");
		rd_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_16.setBounds(27, 499, 160, 23);
		contentPane.add(rd_16);

		rd_17 = new JRadioButton("Phát hiện biên Prewitt");
		rd_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_17.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_17.setBounds(27, 526, 163, 23);
		contentPane.add(rd_17);

		rd_18 = new JRadioButton("Biểu đồ Histogram");
		rd_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
			}
		});
		rd_18.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_18.setBounds(27, 40, 157, 23);
		contentPane.add(rd_18);
		rd_18.setSelected(true);

		rd_19 = new JRadioButton("Cân bằng tần suất");
		rd_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableColourImageProcessing();
				enableTextField();
				if (file != null) {
					sliderNewLever.setEnabled(true);
					lb_newLever.setEnabled(true);
				}
			}
		});
		rd_19.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_19.setBounds(27, 67, 130, 23);
		contentPane.add(rd_19);

		rd_20 = new JRadioButton("Color Image Processing");
		rd_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (file != null) {
					lbHue.setEnabled(true);
					lbSaturation.setEnabled(true);
					lbBrightness.setEnabled(true);
					slider_hue.setEnabled(true);
					slider_saturation.setEnabled(true);
					slider_brightness.setEnabled(true);
					lb_h.setEnabled(true);
					lb_s.setEnabled(true);
					lb_b.setEnabled(true);
					enableTextField();
				}
			}
		});
		rd_20.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rd_20.setBounds(27, 552, 161, 23);
		contentPane.add(rd_20);

		tf_c_power = new JTextField();
		tf_c_power.setEnabled(false);
		tf_c_power.setBounds(302, 229, 27, 20);
		contentPane.add(tf_c_power);
		tf_c_power.setColumns(10);

		lb_h = new JLabel("0.0");
		lb_h.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_h.setBounds(265, 580, 46, 23);
		lb_h.setEnabled(false);
		contentPane.add(lb_h);

		lb_s = new JLabel("0.0");
		lb_s.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_s.setBounds(265, 610, 46, 23);
		lb_s.setEnabled(false);
		contentPane.add(lb_s);

		lb_b = new JLabel("0.0");
		lb_b.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_b.setBounds(265, 640, 46, 23);
		lb_b.setEnabled(false);
		contentPane.add(lb_b);

		JLabel lblGamma = new JLabel("| Gamma:");
		lblGamma.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGamma.setBounds(140, 229, 68, 23);
		contentPane.add(lblGamma);

		tf_gamma = new JTextField();
		tf_gamma.setEnabled(false);
		tf_gamma.setBounds(206, 229, 27, 20);
		contentPane.add(tf_gamma);
		tf_gamma.setColumns(10);

		lb_image = new JLabel("");
		lb_image.setBounds(850, 65, 400, 600);
		contentPane.add(lb_image);

		JLabel lb_image1 = new JLabel("");
		lb_image1.setBounds(354, 65, 400, 600);
		contentPane.add(lb_image1);

		JButton bt_run = new JButton(new ImageIcon("play.png"));
		bt_run.setEnabled(false);
		bt_run.setBounds(370, 5, 45, 45);
		contentPane.add(bt_run);
		bt_save.setEnabled(false);

		bt_save.setBounds(470, 5, 45, 45);
		contentPane.add(bt_save);

		ButtonGroup group = new ButtonGroup();
		group.add(rd_1);
		group.add(rd_2);
		group.add(rd_3);
		group.add(rd_4);
		group.add(rd_5);
		group.add(rd_6);
		group.add(rd_7);
		group.add(rd_8);
		group.add(rd_9);
		group.add(rd_10);
		group.add(rd_11);
		group.add(rd_12);
		group.add(rd_13);
		group.add(rd_14);
		group.add(rd_15);
		group.add(rd_16);
		group.add(rd_17);
		group.add(rd_18);
		group.add(rd_19);
		group.add(rd_20);

		bt_run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ip = new Image_Processing();
				image.setData(img_goc.getData()); // Cập nhật image lại thành gốc
				try {
					if (rd_1.isSelected()) {
						img = ip.grayImage(image);
					} else if (rd_2.isSelected()) {
						img = ip.negativeImage(image);
					} else if (rd_3.isSelected()) {
						img = ip.negativeGrayImage(image);
					} else if (rd_5.isSelected()) {
						if (tf_c.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Constant", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						int constant = Integer.parseInt(tf_c.getText());
						if (constant > 50) {
							JOptionPane.showMessageDialog(null,
									"Constant vượt quá giới hạn cho phép. Khuyến nghị <= 50", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else if (constant < 0) {
							JOptionPane.showMessageDialog(null, "Constant không nhận được giá trị âm", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							img = ip.logaritImage(image, Integer.parseInt(tf_c.getText()));
						}
					} else if (rd_6.isSelected()) {
						if (tf_gamma.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Gamma", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else if (tf_c_power.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Constant", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else if (Integer.parseInt(tf_c_power.getText()) > 255
								|| Integer.parseInt(tf_c_power.getText()) < 0) {
							JOptionPane.showMessageDialog(null, "Constant chỉ nằm trong khoảng 0 - 255", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							img = ip.power_lawImage(image, Integer.parseInt(tf_gamma.getText()),
									Integer.parseInt(tf_c_power.getText()));
						}
					} else if (rd_7.isSelected()) {
						img = ip.laplacianImage(image);
					} else if (rd_8.isSelected()) {
						img = ip.laplacianExtensionImage(image);
					} else if (rd_9.isSelected()) {
						if (tf_bit.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Bit", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						int bit = Integer.parseInt(tf_bit.getText());
						if (bit < 0 || bit > 7) {
							JOptionPane.showMessageDialog(null, "Bit chỉ nằm trong khoảng 0 - 7", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							img = ip.bitPlaneSlicing(image, bit);
						}
					} else if (rd_10.isSelected()) {
						img = ip.minNeighbourhoodImage(image);
					} else if (rd_11.isSelected()) {
						img = ip.maxNeighbourhoodImage(image);
					} else if (rd_12.isSelected()) {
						img = ip.smoothingImage(image);
					} else if (rd_13.isSelected()) {
						img = ip.medianImage(image);
					} else if (rd_14.isSelected()) {
						img = ip.weightSmoothingImage(image);
					} else if (rd_15.isSelected()) {
						img = ip.gradientImage(image);
					} else if (rd_16.isSelected()) {
						img = ip.sobelImage(image);
					} else if (rd_17.isSelected()) {
						img = ip.prewittImage(image);
					} else if (rd_18.isSelected()) {
						repaint();
						return;
					}
					ImageIcon icon = new ImageIcon(img);
					lb_image.setSize(400, 600);
					lb_image.setIcon(icon);
					bt_save.setEnabled(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnConvert.setEnabled(true);
			}
		});

		bt_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showSaveDialog(null); // Nếu có chọn nút Save thì trả về 0
				if (returnVal == jfc.APPROVE_OPTION) { // APPROVE_OPTION chứng tỏ bạn đã chọn Open or Save rồi
					file_save = jfc.getSelectedFile(); // APPROVE_OPTION ở đây luôn = 0
					try {
						ip.save_Image(img, file_save.toString(), "jpg");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		JButton btnOpenFile = new JButton(new ImageIcon("open.png"));
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(null); // Nếu có chọn nút Save thì trả về 0
				if (returnVal == jfc.APPROVE_OPTION) { // APPROVE_OPTION chứng tỏ bạn đã chọn Open or Save rồi
					file = jfc.getSelectedFile(); // APPROVE_OPTION ở đây luôn = 0
					tf_path.setText(file.toString());
					bt_run.setEnabled(true);
					slider_hue.setValue(0);
					slider_saturation.setValue(0);
					slider_brightness.setValue(0);
					sliderNewLever.setValue(255);
					slider_Threshold.setValue(128);
					if (rd_19.isSelected()) {
						sliderNewLever.setEnabled(true);
						lb_newLever.setEnabled(true);
					} else if (rd_4.isSelected()) {
						slider_Threshold.setEnabled(true);
						lb_th.setEnabled(true);
					} else if (rd_20.isSelected()) {
						lbHue.setEnabled(true);
						lbSaturation.setEnabled(true);
						lbBrightness.setEnabled(true);
						slider_hue.setEnabled(true);
						slider_saturation.setEnabled(true);
						slider_brightness.setEnabled(true);
						lb_h.setEnabled(true);
						lb_s.setEnabled(true);
						lb_b.setEnabled(true);
					}
					try {
						image = ImageIO.read(file);
						img_goc = ImageIO.read(file);
						ImageIcon icon_1 = new ImageIcon(image);
						lb_image1.setSize(400, 600);
						lb_image1.setIcon(icon_1);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnOpenFile.setBounds(270, 5, 45, 45);
		contentPane.add(btnOpenFile);

		JLabel lblC = new JLabel("|  Constant:");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblC.setBounds(133, 202, 83, 23);
		contentPane.add(lblC);

		tf_c = new JTextField();
		tf_c.setEnabled(false);
		tf_c.setBounds(215, 202, 27, 20);
		contentPane.add(tf_c);
		tf_c.setColumns(10);

		JLabel lblConstant = new JLabel("Constant:");
		lblConstant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConstant.setBounds(243, 229, 60, 23);
		contentPane.add(lblConstant);

		JLabel lblBit = new JLabel("|    Bit: ");
		lblBit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBit.setBounds(153, 310, 55, 23);
		contentPane.add(lblBit);

		tf_bit = new JTextField();
		tf_bit.setEnabled(false);
		tf_bit.setBounds(196, 310, 27, 20);
		contentPane.add(tf_bit);
		tf_bit.setColumns(10);

		JLabel lblNewLever = new JLabel("| New Lever:");
		lblNewLever.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLever.setBounds(157, 67, 82, 23);
		contentPane.add(lblNewLever);

		slider_hue = new JSlider();
		slider_saturation = new JSlider();
		slider_brightness = new JSlider();
		slider_hue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (file != null) {
					float h = slider_hue.getValue() / 100f;
					lb_h.setText(((h > 0) ? "+" : "") + String.valueOf(h));
					btnConvert.setEnabled(true);
					float temp_h = slider_hue.getValue() / 100f;
					float temp_s = slider_saturation.getValue() / 100f;
					float temp_b = slider_brightness.getValue() / 100f;
					image.setData(img_goc.getData());
					ip = new Image_Processing();
					try {
						img = ip.colourImage(image, temp_h, temp_s, temp_b);
						ImageIcon icon = new ImageIcon(img);
						lb_image.setSize(400, 600);
						lb_image.setIcon(icon);
						bt_save.setEnabled(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		slider_hue.setMajorTickSpacing(10);
		slider_hue.setMinimum(-100);
		slider_hue.setValue(0);
		slider_hue.setPaintTicks(true);
		slider_hue.setBounds(110, 585, 150, 20);
		slider_hue.setEnabled(false);
		contentPane.add(slider_hue);

		slider_saturation.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (file != null) {
					float s = slider_saturation.getValue() / 100f;
					lb_s.setText(((s > 0) ? "+" : "") + String.valueOf(s));
					btnConvert.setEnabled(true);
					float temp_h = slider_hue.getValue() / 100f;
					float temp_s = slider_saturation.getValue() / 100f;
					float temp_b = slider_brightness.getValue() / 100f;
					image.setData(img_goc.getData());
					ip = new Image_Processing();
					try {
						img = ip.colourImage(image, temp_h, temp_s, temp_b);
						ImageIcon icon = new ImageIcon(img);
						lb_image.setSize(400, 600);
						lb_image.setIcon(icon);
						bt_save.setEnabled(true);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		slider_saturation.setMajorTickSpacing(10);
		slider_saturation.setMinimum(-100);
		slider_saturation.setValue(0);
		slider_saturation.setPaintTicks(true);
		slider_saturation.setBounds(110, 615, 150, 20);
		slider_saturation.setEnabled(false);
		contentPane.add(slider_saturation);

		slider_brightness.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (file != null) {
					float b = slider_brightness.getValue() / 100f;
					lb_b.setText(((b > 0) ? "+" : "") + String.valueOf(b));
					btnConvert.setEnabled(true);
					float temp_h = slider_hue.getValue() / 100f;
					float temp_s = slider_saturation.getValue() / 100f;
					float temp_b = slider_brightness.getValue() / 100f;
					image.setData(img_goc.getData());
					ip = new Image_Processing();
					try {
						img = ip.colourImage(image, temp_h, temp_s, temp_b);
						ImageIcon icon = new ImageIcon(img);
						lb_image.setSize(400, 600);
						lb_image.setIcon(icon);
						bt_save.setEnabled(true);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		slider_brightness.setMajorTickSpacing(10);
		slider_brightness.setMinimum(-100);
		slider_brightness.setValue(0);
		slider_brightness.setPaintTicks(true);
		slider_brightness.setBounds(110, 645, 150, 20);
		slider_brightness.setEnabled(false);
		contentPane.add(slider_brightness);

		lbHue = new JLabel("Hue");
		lbHue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbHue.setBounds(35, 580, 46, 23);
		lbHue.setEnabled(false);
		contentPane.add(lbHue);

		lbSaturation = new JLabel("Saturation");
		lbSaturation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbSaturation.setBounds(35, 610, 83, 23);
		lbSaturation.setEnabled(false);
		contentPane.add(lbSaturation);

		lbBrightness = new JLabel("Brightness");
		lbBrightness.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbBrightness.setBounds(35, 640, 76, 23);
		lbBrightness.setEnabled(false);
		contentPane.add(lbBrightness);

		btnConvert = new JButton(new ImageIcon("swap.png"));
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rd_18.isSelected())
					return;
				image = img;
				for (int i = 0; i < img_goc.getWidth(); i++) {
					for (int j = 0; j < img_goc.getHeight(); j++) {
						img_goc.setRGB(i, j, img.getRGB(i, j));
					}
				}
				ImageIcon icon_1 = new ImageIcon(image);
				lb_image1.setSize(400, 600);
				lb_image1.setIcon(icon_1);
			}
		});
		btnConvert.setBounds(764, 299, 80, 70);
		btnConvert.setEnabled(false);
		contentPane.add(btnConvert);

		sliderNewLever = new JSlider();
		sliderNewLever.setMajorTickSpacing(51);
		sliderNewLever.setEnabled(false);
		sliderNewLever.setMaximum(255);
		sliderNewLever.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (file != null) {
					lb_newLever.setText(String.valueOf(sliderNewLever.getValue()));
					btnConvert.setEnabled(true);
					image.setData(img_goc.getData());
					ip = new Image_Processing();
					try {
						img = ip.frequencyBalancingImage(image, sliderNewLever.getValue());
						ImageIcon icon = new ImageIcon(img);
						lb_image.setSize(400, 600);
						lb_image.setIcon(icon);
						bt_save.setEnabled(true);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		sliderNewLever.setValue(255);
		sliderNewLever.setPaintTicks(true);
		sliderNewLever.setBounds(235, 72, 80, 20);
		contentPane.add(sliderNewLever);

		lb_newLever = new JLabel("255");
		lb_newLever.setEnabled(false);
		lb_newLever.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_newLever.setBounds(320, 67, 27, 23);
		contentPane.add(lb_newLever);

		slider_Threshold = new JSlider();
		slider_Threshold.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (file != null) {
					lb_th.setText(String.valueOf(slider_Threshold.getValue()));
					btnConvert.setEnabled(true);
					image.setData(img_goc.getData());
					ip = new Image_Processing();
					try {
						img = ip.binaryImage(image, slider_Threshold.getValue());
						ImageIcon icon = new ImageIcon(img);
						lb_image.setSize(400, 600);
						lb_image.setIcon(icon);
						bt_save.setEnabled(true);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		slider_Threshold.setEnabled(false);
		slider_Threshold.setMinorTickSpacing(51);
		slider_Threshold.setMaximum(255);
		slider_Threshold.setValue(128);
		slider_Threshold.setPaintTicks(true);
		slider_Threshold.setBounds(217, 180, 80, 20);
		contentPane.add(slider_Threshold);

		lb_th = new JLabel("128");
		lb_th.setEnabled(false);
		lb_th.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_th.setBounds(298, 175, 32, 23);
		contentPane.add(lb_th);
	}

	public void enableColourImageProcessing() {
		lbHue.setEnabled(false);
		lbSaturation.setEnabled(false);
		lbBrightness.setEnabled(false);
		slider_hue.setEnabled(false);
		slider_saturation.setEnabled(false);
		slider_brightness.setEnabled(false);
		lb_h.setEnabled(false);
		lb_s.setEnabled(false);
		lb_b.setEnabled(false);
	}

	public void enableTextField() {
		lb_th.setEnabled(false);
		slider_Threshold.setEnabled(false);
		lb_newLever.setEnabled(false);
		sliderNewLever.setEnabled(false);
		tf_c.setEnabled(false);
		tf_gamma.setEnabled(false);
		tf_c_power.setEnabled(false);
		tf_bit.setEnabled(false);
	}
}
