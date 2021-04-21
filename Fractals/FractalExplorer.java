
import java.awt.BorderLayout;

import fractal.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.geom.Rectangle2D;


public class FractalExplorer{

	private FractalGenerator fractalGen;
	public int side;
	public Rectangle2D.Double range;
	public JImageDisplay image;
	private Handler handler = new Handler();

	JComboBox<String> selectBox;
	JLabel selectLabel;
	JButton resetButton;
	JButton saveButton;


	public static void main(String[] args){
		FractalExplorer fractalExplorer = new FractalExplorer(Integer.parseInt(args[0]));
		fractalExplorer.createAndShowGUI();
		fractalExplorer.drawFractal();

	}

	
	private void drawFractal(){
		for(int x =0; x<side;x++){

			double xCoord = FractalGenerator.getCoord(range.x,range.x+range.width, side, x);
			
			for (int y = 0; y<side;y++){

				double yCoord = FractalGenerator.getCoord(range.y,range.y+range.height, side, y);

				int numIters = fractalGen.numIterations(xCoord,yCoord);
				int rgbColor;

				if (numIters != -1){
					float hue = 0.6f + (float) numIters / 200f;
  					rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
  				} else rgbColor = 0;
  				image.drawPixel(x, y, rgbColor);
			}
		}
		image.repaint();
	}

	private void createAndShowGUI(){
		JFrame frame = new JFrame("Fractal Explorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JPanel buttonPanel = new JPanel();
		JPanel selectPanel = new JPanel();

		image = new JImageDisplay(side, side);
		
		frame.getContentPane().add(image, BorderLayout.CENTER);
		
		

		selectBox = new JComboBox<String>();
		selectLabel = new JLabel("Fractal:  ");
		resetButton = new JButton("Сброс");
		saveButton = new JButton("Сохранить");

		resetButton.setPreferredSize(new Dimension(200,50));
		resetButton.setVisible(true);
		resetButton.setActionCommand("reset");
        resetButton.addActionListener(handler);

        saveButton.setPreferredSize(new Dimension(200,50));
		saveButton.setVisible(true);
		saveButton.setActionCommand("save");
        saveButton.addActionListener(handler);


        selectLabel.setVisible(true);
        selectBox.setPreferredSize(new Dimension(300,30));
        selectBox.addItem("Mandelbrot");
        selectBox.addItem("Tricorn");
        selectBox.addItem("Burning Ship");
        selectBox.addActionListener(handler);

		selectPanel.add(selectLabel);        	
        selectPanel.add(selectBox);

        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);


        frame.getContentPane().add(selectPanel, BorderLayout.NORTH);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		frame.getContentPane().addMouseListener(new Mouse());

		frame.pack();
  		frame.setVisible(true);
 		frame.setResizable(false);
	}

	public FractalExplorer(int side){
		this.side = side;
		fractalGen = new Mandelbrot();
		range = new Rectangle2D.Double();
        fractalGen.getInitialRange(range);
	}

	private class Handler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			if (command.equals("reset")){
				range = new Rectangle2D.Double();
                fractalGen.getInitialRange(range);
				drawFractal();
			}
			else if (command.equals("save")){
				JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String filePath = file.getPath();
                    if (!filePath.toLowerCase().endsWith(".png")) {
                        file = new File(filePath + ".png");
                    }
                    try {
                        ImageIO.write(image.getImage(), "png", file);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error: Couldn't save image ( "
                                + ioException.getMessage() + " )");
                    }
                }
			}
			else if (e.getSource() == selectBox){
				if(selectBox.getSelectedItem().equals("Mandelbrot"))	fractalGen = new Mandelbrot();
				if(selectBox.getSelectedItem().equals("Tricorn")) fractalGen = new Tricorn();
				if(selectBox.getSelectedItem().equals("Burning Ship")) fractalGen = new BurningShip();
				range = new Rectangle2D.Double();
				fractalGen.getInitialRange(range);
				drawFractal();			
			}
			
		}
	}
	private class Mouse extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, side, e.getX());
			double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, side, e.getY());
		
			fractalGen.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
			drawFractal();
		}

	}
}