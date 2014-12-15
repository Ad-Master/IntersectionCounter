package pl.grm.zbiory;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class IntersectionCounterWindow extends JFrame {
	private static final long	serialVersionUID	= 1L;
	private IntersectionCounter	rownania;
	private JPanel				contentPane;
	private JPanel				labelPanel;
	private DrawPanel			drawPanel;
	private JLabel				pointAmountLabel;
	private JLabel				linesNumberLabel;
	private JLabel				linesIntersectionsLabel;
	private JButton				startButton;
	private JButton				closeButton;
	private JPanel				buttonPanel;
	
	/**
	 * Create the frame.
	 */
	public IntersectionCounterWindow() {
		setTitle("Zbiory danych");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 436);
		setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		Font font = new Font("Tahoma", Font.BOLD, 16);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		labelPanel = new JPanel();
		contentPane.add(labelPanel, BorderLayout.WEST);
		labelPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label1 = new JLabel("Iloœc punktów: ");
		labelPanel.add(label1);
		pointAmountLabel = new JLabel();
		pointAmountLabel.setFont(font);
		labelPanel.add(pointAmountLabel);
		JLabel label2 = new JLabel("Iloœc odcinków: ");
		labelPanel.add(label2);
		linesNumberLabel = new JLabel();
		linesNumberLabel.setFont(font);
		labelPanel.add(linesNumberLabel);
		JLabel label3 = new JLabel("Iloœc przeciec: ");
		labelPanel.add(label3);
		linesIntersectionsLabel = new JLabel();
		linesIntersectionsLabel.setFont(font);
		labelPanel.add(linesIntersectionsLabel);
		
		drawPanel = new DrawPanel();
		contentPane.add(drawPanel, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		startButton = new JButton("Generuj");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generateLines();
			}
		});
		buttonPanel.add(startButton);
		closeButton = new JButton("Zamknij");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(closeButton);
	}
	
	private void generateLines() {
		rownania = new IntersectionCounter();
		drawPanel.setRownania(rownania);
		drawPanel.repaint();
		
		String linesNumber = String.valueOf(rownania.lines.size());
		String intersectionCount = String.valueOf(rownania.intersectionCount);
		String pointAmount = String.valueOf(rownania.pointAmount);
		
		SwingUtilities.updateComponentTreeUI(drawPanel);
		linesNumberLabel.setText(linesNumber);
		linesIntersectionsLabel.setText(intersectionCount);
		pointAmountLabel.setText(pointAmount);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					IntersectionCounterWindow frame = new IntersectionCounterWindow();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
