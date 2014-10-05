import java.awt.*;
import java.time.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;

public class clock_GUI extends JFrame {

	/**
	 * @author Florescu
	 * @since 04.09.2014 Simple MultiThread clock
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel contentPane;
	static JLabel hour;
	static JLabel minute;
	static JLabel seconds;
	static JLabel nano;
	static JLabel separator1;
	static JLabel separator2;
	static JLabel separator3;
	static int i = 0;
	private JMenuItem alarm;
	private final Action openAlarm = new openAlarmAction();
	private JMenuItem exitProgram;
	private final Action exit = new exitAction();
	private JMenuBar menuBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clock_GUI frame = new clock_GUI();
					frame.setVisible(true);
					start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public clock_GUI() {
		setResizable(false);

		setTitle("Clock");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Eclipse WorkSpace\\Clock\\img\\clock.png"));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 206);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		hour = new JLabel();
		hour.setHorizontalAlignment(SwingConstants.CENTER);
		hour.setText("h");
		hour.setForeground(new Color(0, 0, 255));
		hour.setFont(new Font("Tahoma", Font.BOLD, 57));
		hour.setBounds(0, 13, 80, 140);
		contentPane.add(hour);

		separator1 = new JLabel(":");
		separator1.setFont(new Font("Tahoma", Font.PLAIN, 57));
		separator1.setBounds(98, 13, 20, 140);
		contentPane.add(separator1);

		minute = new JLabel();
		minute.setHorizontalAlignment(SwingConstants.CENTER);
		minute.setText("m");
		minute.setForeground(new Color(0, 128, 128));
		minute.setFont(new Font("Tahoma", Font.PLAIN, 57));
		minute.setBounds(130, 13, 78, 140);
		contentPane.add(minute);

		separator2 = new JLabel(":");
		separator2.setFont(new Font("Tahoma", Font.PLAIN, 57));
		separator2.setBounds(212, 13, 26, 140);
		contentPane.add(separator2);

		seconds = new JLabel();
		seconds.setHorizontalAlignment(SwingConstants.CENTER);
		seconds.setText("s");
		seconds.setForeground(new Color(255, 0, 0));
		seconds.setFont(new Font("Tahoma", Font.ITALIC, 45));
		seconds.setBounds(220, 18, 98, 140);
		contentPane.add(seconds);

		separator3 = new JLabel(":");
		separator3.setFont(new Font("Tahoma", Font.PLAIN, 57));
		separator3.setBounds(311, 13, 26, 140);
		contentPane.add(separator3);

		nano = new JLabel();
		nano.setText("n");
		nano.setHorizontalAlignment(SwingConstants.CENTER);
		nano.setForeground(new Color(255, 69, 0));
		nano.setFont(new Font("Tahoma", Font.ITALIC, 40));
		nano.setBounds(330, 18, 235, 140);
		contentPane.add(nano);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 111, 23);
		contentPane.add(menuBar);

		JMenu options = new JMenu("Options");
		menuBar.add(options);

		alarm = new JMenuItem("Alarms");
		alarm.setIcon(new ImageIcon("E:\\Eclipse WorkSpace\\Clock\\img\\alarm.jpg"));
		alarm.setHorizontalAlignment(SwingConstants.LEFT);
		alarm.setAction(openAlarm);
		alarm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		options.add(alarm);

		exitProgram = new JMenuItem("Exit");
		exitProgram.setHorizontalAlignment(SwingConstants.LEFT);
		exitProgram.setAction(exit);
		exitProgram.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		options.add(exitProgram);

	}

	private static void start() {
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {

				for (;;) {
					LocalDateTime time = LocalDateTime.now();
					hour.setText(Integer.toString(time.getHour()));

					if (time.getMinute() <= 9)
						minute.setText("0" + time.getMinute());
					else
						minute.setText(Integer.toString(time.getMinute()));

					if (time.getSecond() <= 9)
						seconds.setText("0" + time.getSecond());
					else
						seconds.setText(Integer.toString(time.getSecond()));
					// Toolkit.getDefaultToolkit().beep();
					nano.setText(Integer.toString(time.getNano()));

					if (time.getSecond() % 2 == 0) {
						separator1.setText(":");
						separator2.setText(":");
						separator3.setText(":");
					} else {
						separator1.setText(" ");
						separator2.setText(" ");
						separator3.setText(" ");
					}

					Thread.sleep(1);
				}

			}
		};
		worker.execute();
	}

	private class openAlarmAction extends AbstractAction {
		
		private static final long serialVersionUID = 32252845951819863L;

		public openAlarmAction() {
			putValue(NAME, "Alarms");
			putValue(SHORT_DESCRIPTION, "You can set alarms when need");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class exitAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;

		public exitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit clock");
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
