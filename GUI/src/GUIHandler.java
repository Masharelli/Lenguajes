import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIHandler {
	
	private JProgressBar bar;
	private JTable toDo;
	private JTable finished;
	private DefaultTableModel modelToDo;
	private DefaultTableModel modelFinished;
	private JSpinner spinerFinishedTasks;
	private int nfinishedTasks;
	
	
	public GUIHandler(JTable toDo, JTable finished, JProgressBar bar, JSpinner spinnerFinishedTasks) {
		this.toDo = toDo;
		this.finished = finished;
		this.bar = bar;
		this.spinerFinishedTasks = spinnerFinishedTasks;
		
		this.modelToDo = (DefaultTableModel) this.toDo.getModel();
		this.modelFinished = (DefaultTableModel) this.finished.getModel();
		
		this.modelToDo.setRowCount(0);
		this.modelFinished.setRowCount(0);
		
		this.nfinishedTasks = 0;
	}
	
	public void addTask(String id, String operacion) {
		this.modelToDo.addRow(new String[] {id, operacion});
	}
	
	public void addFinishedTask(String id, String operacion, String resultado) {
		this.modelFinished.addRow(new String[] {id, operacion, resultado});
		this.nfinishedTasks++;
	}
	
	public void updateBar(int currentSize, int maxSize) {
		this.bar.setValue(100 - (currentSize * 100 / maxSize));
		System.out.println("valor de la barra " + (100 - (currentSize * 100 / maxSize)));
	}
	
	public void consumeUpdate(String id, String operacion, String resultado, int bufferSize, int currentSize) {
		addFinishedTask(id, operacion, resultado);
		updateBar(currentSize, bufferSize);
		this.spinerFinishedTasks.setValue((int) nfinishedTasks);
		
		this.toDo.setRowSelectionInterval(0, 0);
		this.modelToDo.removeRow(this.toDo.getSelectedRow());
	}
	
	public void produceUpdate(String id, String operacion, int bufferSize, int currentSize) {
		addTask(id, operacion);
		updateBar(currentSize, bufferSize);
	}
}
