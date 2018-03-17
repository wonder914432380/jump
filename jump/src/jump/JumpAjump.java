package jump;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class JumpAjump extends JFrame{
		private JLabel label;
		boolean flag=false;
		int x0,y0,x1,y1;
		
		
	public JumpAjump() {
		
		super("微信跳一跳");//新增窗口
		System.out.println("程序启动");
		this.setUndecorated(true);
		this.setOpacity(0.4f);//透明度
		this.setAlwaysOnTop(true);
		this.setSize(340,600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.toFront();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 final JLabel label=new JLabel("右键点击");
		this.add(label);
		
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton()==MouseEvent.BUTTON3){
					if(!flag){
						x0=e.getX();
						y0=e.getY();
					String banner="鼠标当前点击的位置是:"+x0+","+y0;
					label.setText(banner);
					System.out.println("鼠标当前点击的位置是:"+x0+","+y0);
					flag=true;
					}
					else {
						x1=e.getX();
						y1=e.getY();
						String banner1="鼠标当前点击的位置是:"+x1+","+y1;
						label.setText(banner1);
						System.out.println("鼠标当前点击的位置是:"+x1+","+y1);
						double _x=Math.abs(x0-x1);
						double _y=Math.abs(y0-y1);
						
						double dis=Math.sqrt(_x*_x+_y*_y);
						label.setText(Math.ceil(dis)*4.8+"");
						flag=false;
						
						String cmd="adb shell input touchscreen swipe 200 187 170 200 "+Math.round(dis*4.1);
						Runtime run=Runtime.getRuntime();
						try {
							Process pr=run.exec(cmd);
							System.out.println(cmd);
							pr.waitFor();
						} catch (Exception e1) {
							// TODO: handle exception
							e1.printStackTrace();
							System.out.println(e1);
						}
					}
				}
			}
			
		});
		
		
	}
	
	public static void main(String[] args) {
		new JumpAjump();
	}
	
	
}
