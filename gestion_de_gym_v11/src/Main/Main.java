package Main;

import java.sql.SQLException;

import Controller.ClientController;
import Model.ClientModel;
import View.clientView;

public class Main {

	public static void main(String[] args) throws SQLException {
		ClientModel model = new ClientModel();
		clientView view = new clientView();
		ClientController controller = new ClientController(model, view);

		view.setVisible(true);
		controller.table_load();

	}

}
