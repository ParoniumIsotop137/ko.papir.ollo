package ko.papir.ollo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class KoPapirOlloMain implements ActionListener {

	private JFrame frmJatek;
	private JButton btnKo;
	private JButton btnPapir;
	private JButton btnOllo;

	private JButton btnSorsolás;

	private JButton btnTippeles;

	private JButton valasztott;

	private JButton sorsoltButton;
	private GombTipus tipus;

	private List<JButton> gombLista;

	private int valasztottGomb;

	private int sorsoltGomb;

	private Random r = new Random();
	private JLabel lblEredmeny;
	private JLabel lblMagyarazat;
	private JButton btnUjJatek;
	private JButton btnSzabalyok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KoPapirOlloMain window = new KoPapirOlloMain();
					window.frmJatek.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KoPapirOlloMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJatek = new JFrame();
		frmJatek.setTitle("Kő, papír, olló játék");
		frmJatek.setBounds(250, 50, 700, 600);
		frmJatek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJatek.getContentPane().setLayout(null);

		gombLista = new ArrayList<JButton>();

		btnTippeles = new JButton("Tipp rögzítése");
		btnTippeles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				TippetMutat();
				btnTippeles.setEnabled(false);
				btnSorsolás.setEnabled(true);
			}
		});

		btnSzabalyok = new JButton("Játékszabályok");
		btnSzabalyok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(frmJatek,
						"Játékszabályok:\n-az olló elvágja a papírt: az olló nyer\n-a kő kicsorbítja az ollót: a kő nyer\n-a papír becsomagolja a követ: a papír nyer\n-egyezőség esetén döntetlen",
						"Szabályok", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnSzabalyok.setForeground(new Color(0, 0, 205));
		btnSzabalyok.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btnSzabalyok.setBackground(new Color(245, 222, 179));
		btnSzabalyok.setBounds(494, 463, 165, 30);
		frmJatek.getContentPane().add(btnSzabalyok);

		btnUjJatek = new JButton("Új játék");
		btnUjJatek.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				UjJatek();

			}
		});
		btnUjJatek.setBackground(new Color(245, 222, 179));
		btnUjJatek.setForeground(new Color(0, 0, 205));
		btnUjJatek.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btnUjJatek.setBounds(10, 463, 100, 30);
		btnUjJatek.setEnabled(false);
		frmJatek.getContentPane().add(btnUjJatek);

		lblMagyarazat = new JLabel("");
		lblMagyarazat.setHorizontalAlignment(SwingConstants.CENTER);
		lblMagyarazat.setForeground(new Color(210, 105, 30));
		lblMagyarazat.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblMagyarazat.setBounds(120, 508, 423, 30);
		frmJatek.getContentPane().add(lblMagyarazat);

		lblEredmeny = new JLabel("");
		lblEredmeny.setHorizontalAlignment(SwingConstants.CENTER);
		lblEredmeny.setForeground(new Color(210, 105, 30));
		lblEredmeny.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 22));
		lblEredmeny.setBounds(244, 463, 180, 30);
		frmJatek.getContentPane().add(lblEredmeny);

		JLabel lblGepTipp = new JLabel("A gép tippje:");
		lblGepTipp.setOpaque(true);
		lblGepTipp.setHorizontalAlignment(SwingConstants.CENTER);
		lblGepTipp.setForeground(new Color(165, 42, 42));
		lblGepTipp.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 18));
		lblGepTipp.setBackground(new Color(240, 248, 255));
		lblGepTipp.setBounds(455, 282, 120, 25);
		frmJatek.getContentPane().add(lblGepTipp);

		JLabel lblTipped = new JLabel("A te tipped:");
		lblTipped.setOpaque(true);
		lblTipped.setBackground(new Color(240, 248, 255));
		lblTipped.setForeground(new Color(165, 42, 42));
		lblTipped.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 18));
		lblTipped.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipped.setBounds(80, 282, 120, 25);
		frmJatek.getContentPane().add(lblTipped);

		btnSorsolás = new JButton("Sorsolás");
		btnSorsolás.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Sorsolas();

			}
		});
		btnSorsolás.setForeground(new Color(255, 0, 0));
		btnSorsolás.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnSorsolás.setBackground(new Color(255, 228, 196));
		btnSorsolás.setBounds(420, 236, 200, 35);
		frmJatek.getContentPane().add(btnSorsolás);
		btnTippeles.setBackground(new Color(255, 239, 213));
		btnTippeles.setForeground(new Color(100, 149, 237));
		btnTippeles.setFont(new Font("Segoe Script", Font.BOLD, 18));
		btnTippeles.setBounds(49, 236, 200, 35);
		btnTippeles.setEnabled(false);
		btnSorsolás.setEnabled(false);
		frmJatek.getContentPane().add(btnTippeles);

		JLabel lblKiiras = new JLabel("Válaszd ki tippedet valamelyik képére  kattintva:");
		lblKiiras.setForeground(new Color(210, 105, 30));
		lblKiiras.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 20));
		lblKiiras.setHorizontalAlignment(SwingConstants.CENTER);
		lblKiiras.setBounds(80, 26, 543, 30);
		frmJatek.getContentPane().add(lblKiiras);

		JLabel lblOllo = new JLabel("Olló");
		lblOllo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOllo.setForeground(new Color(255, 69, 0));
		lblOllo.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblOllo.setBounds(543, 73, 80, 25);
		frmJatek.getContentPane().add(lblOllo);

		JLabel lblPapir = new JLabel("Papír");
		lblPapir.setHorizontalAlignment(SwingConstants.CENTER);
		lblPapir.setForeground(new Color(255, 69, 0));
		lblPapir.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblPapir.setBounds(309, 73, 80, 25);
		frmJatek.getContentPane().add(lblPapir);

		JLabel lblKo = new JLabel("Kő");
		lblKo.setHorizontalAlignment(SwingConstants.CENTER);
		lblKo.setForeground(new Color(255, 69, 0));
		lblKo.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblKo.setBounds(80, 73, 80, 25);
		frmJatek.getContentPane().add(lblKo);

		btnOllo = new JButton("");
		btnOllo.addActionListener(this);
		btnOllo.setBorder(new MatteBorder(5, 5, 5, 5, new Color(240, 255, 255)));
		btnOllo.setIcon(new ImageIcon(KoPapirOlloMain.class.getResource("/ko/papir/ollo/ollo.jpg")));
		btnOllo.setBounds(535, 112, 100, 100);
		frmJatek.getContentPane().add(btnOllo);

		btnPapir = new JButton("");
		btnPapir.addActionListener(this);
		btnPapir.setBorder(new MatteBorder(5, 5, 5, 5, new Color(240, 255, 255)));
		btnPapir.setIcon(new ImageIcon(KoPapirOlloMain.class.getResource("/ko/papir/ollo/papir.jpg")));
		btnPapir.setBounds(294, 109, 100, 100);
		frmJatek.getContentPane().add(btnPapir);

		btnKo = new JButton("");
		btnKo.addActionListener(this);
		btnKo.setBorder(new MatteBorder(5, 5, 5, 5, new Color(245, 245, 245)));
		btnKo.setIcon(new ImageIcon(KoPapirOlloMain.class.getResource("/ko/papir/ollo/ko.png")));
		btnKo.setBounds(69, 112, 100, 100);
		frmJatek.getContentPane().add(btnKo);

		JLabel lblHatter = new JLabel("");
		lblHatter.setVerticalAlignment(SwingConstants.TOP);
		lblHatter.setHorizontalAlignment(SwingConstants.CENTER);
		lblHatter.setFont(new Font("Yu Gothic", Font.BOLD | Font.ITALIC, 16));
		lblHatter.setIcon(new ImageIcon(KoPapirOlloMain.class.getResource("/ko/papir/ollo/hatter.jpg")));
		lblHatter.setBounds(0, 0, 685, 565);
		frmJatek.getContentPane().add(lblHatter);

		gombLista.add(btnOllo);
		gombLista.add(btnPapir);
		gombLista.add(btnKo);

	}

	private void UjJatek() {

		frmJatek.getContentPane().remove(valasztott);
		frmJatek.getContentPane().remove(sorsoltButton);
		valasztottGomb = -1;
		sorsoltGomb = -1;
		lblEredmeny.setText("");
		lblMagyarazat.setText("");
		btnUjJatek.setEnabled(false);
		frmJatek.repaint();

	}

	private void Sorsolas() {

		sorsoltButton = new JButton();
		sorsoltGomb = r.nextInt(3);

		sorsoltButton.setBorder(gombLista.get(sorsoltGomb).getBorder());
		sorsoltButton.setIcon(gombLista.get(sorsoltGomb).getIcon());
		sorsoltButton.setBounds(460, 330, 100, 100);
		frmJatek.getContentPane().add(sorsoltButton);
		sorsoltButton.grabFocus();
		btnSorsolás.setEnabled(false);

		EredenyKiiras();
		btnUjJatek.setEnabled(true);

	}

	private void EredenyKiiras() {

		switch (sorsoltGomb) {
		case 0:
			if (valasztottGomb == sorsoltGomb) {
				lblEredmeny.setText("Döntetlen!");
			} else if (valasztottGomb == 1) {
				lblEredmeny.setText("Vesztettél!");
				lblMagyarazat.setText("Az olló elvágja a papírt!");
			} else if (valasztottGomb == 2) {
				lblEredmeny.setText("Nyertél!");
				lblMagyarazat.setText("A kő kicsorbítja az ollót!");
			}
			break;
		case 1:
			if (valasztottGomb == sorsoltGomb) {
				lblEredmeny.setText("Döntetlen!");
			} else if (valasztottGomb == 0) {
				lblEredmeny.setText("Nyertél!");
				lblMagyarazat.setText("Az olló elvágja a papírt!");
			} else if (valasztottGomb == 2) {
				lblEredmeny.setText("Vesztettél!");
				lblMagyarazat.setText("A papír becsomagolja a követ!");
			}
			break;
		case 2:
			if (valasztottGomb == sorsoltGomb) {
				lblEredmeny.setText("Döntetlen!");
			} else if (valasztottGomb == 0) {
				lblEredmeny.setText("Vesztettél!");
				lblMagyarazat.setText("A kő kicsorbítja az ollót!");
			} else if (valasztottGomb == 1) {
				lblEredmeny.setText("Nyertél!");
				lblMagyarazat.setText("A papír becsomagolja a követ!");
			}
			break;
		default:
			break;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOllo) {
			btnOllo.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 70, 0)));
			tipus = GombTipus.OLLO;
			btnTippeles.setEnabled(true);
		} else {
			btnOllo.setBorder(new MatteBorder(5, 5, 5, 5, new Color(240, 255, 255)));

		}

		if (e.getSource() == btnPapir) {
			btnPapir.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 70, 0)));
			tipus = GombTipus.PAPIR;
			btnTippeles.setEnabled(true);
		} else {
			btnPapir.setBorder(new MatteBorder(5, 5, 5, 5, new Color(240, 255, 255)));

		}

		if (e.getSource() == btnKo) {
			btnKo.setBorder(new MatteBorder(5, 5, 5, 5, new Color(255, 70, 0)));
			tipus = GombTipus.KO;
			btnTippeles.setEnabled(true);
		} else {
			btnKo.setBorder(new MatteBorder(5, 5, 5, 5, new Color(240, 255, 255)));

		}

	}

	private void TippetMutat() {

		IndexSzamolas();

		valasztott = new JButton();
		valasztott.setBorder(gombLista.get(valasztottGomb).getBorder());
		valasztott.setIcon(gombLista.get(valasztottGomb).getIcon());
		valasztott.setBounds(93, 330, 100, 100);
		frmJatek.getContentPane().add(valasztott);

	}

	private void IndexSzamolas() {
		if (tipus == GombTipus.OLLO) {
			valasztottGomb = 0;
		} else if (tipus == GombTipus.PAPIR) {
			valasztottGomb = 1;
		}

		else if (tipus == GombTipus.KO) {
			valasztottGomb = 2;
		}

	}
}
