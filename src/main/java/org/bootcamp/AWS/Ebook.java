package org.bootcamp.AWS;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.github.mertakdut.BookSection;
import com.github.mertakdut.Reader;
import com.github.mertakdut.exception.OutOfPagesException;
import com.github.mertakdut.exception.ReadingException;

import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.text.Document;




import org.springframework.util.StringUtils;

public class Ebook {

	
	private JFrame frame2;
	private JFrame frame;
	Reader reader = new Reader();
	JTextPane textPane;
	int page = 0;
    private JTextArea tarea =  new JTextArea(10, 10);
    private JTextField tfield = new JTextField(10);
    private JFrame frame3=new JFrame();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ebook window = new Ebook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		System.out.println("file url location: " + new java.io.File("").getAbsolutePath());
		System.out.println("file url location: " + new java.io.File("").getPath());

		File f1 = new File("Books.dat");
		if (f1.exists()) {
			System.out.println("exists");
			System.out.println("not created");
		} else {
			System.out.println("not exists");
			System.out.println("created file");
			try {
				f1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			FileInputStream fin;
			fin = new FileInputStream(f1);
			BufferedReader br = new BufferedReader(new InputStreamReader(fin));
			String line = null;
			List<String> li = new LinkedList<String>();
			try {
				while ((line = br.readLine()) != null) {
					System.out.println("output list entries follow");
					System.out.println(line);
					li.add(line);

				}

				String str;
				while (((str = br.readLine())) != null) {
					String[] ar = str.split(",");
				}
				br.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		////new Ebook();
		
		

	}

	/**
	 * Create the application.
	 */
	public Ebook() {
		initialize();
		/*frame2 = new JFrame();
		frame2.setBounds(100, 100, 450, 300);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);

		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page--;
				displayPage(page);

			}
		});
		toolBar.add(backButton);

		JButton forewardButton = new JButton("Forward");
		forewardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
				displayPage(page);
			}

		});
		toolBar.add(forewardButton);

		JButton btnNewButton = new JButton("Books List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BookSection content;
				try {
					content = reader.readSection(3);
					String stringOfContent = content.getSectionTextContent(); // Excludes
																				// html
																				// tags.
					textPane.setText(stringOfContent);
				} catch (ReadingException e) {
					e.printStackTrace();
				} catch (OutOfPagesException e) {
					e.printStackTrace();
				}
				
				frame2 = new JFrame();
				frame2.setBounds(100, 100, 450, 300);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
				
			       frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			       
			        ////tarea.setText("Hello there\n");
			        ////tarea.append("Hello student://");
			        
			        
					File f1 = new File("Books.dat");
					if (f1.exists()) {
						System.out.println("exists");
						System.out.println("not created");
					} else {
						System.out.println("not exists");
						System.out.println("created file");
						try {
							f1.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						FileInputStream fin;
						fin = new FileInputStream(f1);
						BufferedReader br = new BufferedReader(new InputStreamReader(fin));
						String line = null;
						List<String> li = new LinkedList<String>();
						try {
							while ((line = br.readLine()) != null) {
								System.out.println("output list entries follow");
								System.out.println(line);
								li.add(line);
								tarea.append(line+"\n");

							}

							String str;
							while (((str = br.readLine())) != null) {
								String[] ar = str.split(",");
							}
							br.close();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
			        
			        JScrollPane scroll = new JScrollPane(tarea);

			        tfield.addActionListener(new ActionListener()
			        {
			            public void actionPerformed(ActionEvent ae)
			            {
			                tarea.append(tfield.getText() + "\n");
			            }
			        });

			        tarea.addMouseListener(new MouseAdapter()
			        {
			            public void mouseClicked(MouseEvent me)
			            {
			                int x = me.getX();
			                int y = me.getY();
			                System.out.println("X : " + x);
			                System.out.println("Y : " + y);
			                int startOffset = tarea.viewToModel(new Point(x, y));
			                System.out.println("Start Offset : " + startOffset);
			                String text = tarea.getText();
			                int searchLocation = text.indexOf("student://", startOffset);
			                System.out.println("Search Location : " + searchLocation);
			                if (searchLocation == startOffset)
			                    JOptionPane.showMessageDialog(scroll, Ebook.this, "BINGO you found me.", searchLocation);
			            }
			        });

			        frame3.getContentPane().add(scroll, BorderLayout.CENTER);
			        frame3.getContentPane().add(tfield, BorderLayout.PAGE_END);
			        frame3.pack();
			        frame3.setLocationByPlatform(true);
			        frame3.setVisible(true);

			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		toolBar.add(btnNewButton);
		
		
		
		JButton btnAddABook = new JButton("Add a Book");
		btnAddABook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

								

			}
		});
		btnAddABook.setHorizontalAlignment(SwingConstants.RIGHT);
		toolBar.add(btnAddABook);
		
		
		

		textPane = new JTextPane();
		frame.getContentPane().add(textPane, BorderLayout.CENTER);

		reader.setMaxContentPerSection(3000); // Max string length for the
												// current page.
		reader.setIsIncludingTextContent(true); // Optional, to return the
												// tags-excluded version.
		try {
			reader.setFullContent("Metamorphosis-jackson.epub");
		} catch (ReadingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // Must call before readSection.

		displayPage(page);

	}
	
	
	

	public void displayPage(int page) {

		BookSection bookSection;
		try {
			bookSection = reader.readSection(page);
			String sectionContent = bookSection.getSectionContent(); // Returns
																		// content
																		// as
																		// html.
			String sectionTextContent = bookSection.getSectionTextContent(); // Excludes
																				// html
																				// tags.
			System.out.println(sectionTextContent);
			textPane.setText(sectionTextContent);
			System.out.println("getSectionContent()");
			///// System.out.println(bookSection.getSectionContent());
			// System.out.println(bookSection.getSectionTextContent());
			/// System.out.println(bookSection.getLabel());

		} catch (ReadingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OutOfPagesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
