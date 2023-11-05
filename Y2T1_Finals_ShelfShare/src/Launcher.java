import java.awt.EventQueue;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(() -> {
			try {
				SuperMain window = new SuperMain();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
