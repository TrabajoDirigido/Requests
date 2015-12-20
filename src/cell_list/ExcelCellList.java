package cell_list;

import java.util.List;

import javafx.util.Pair;

public interface ExcelCellList<E> {
	List<Pair<E,E>> getPairs(ExcelCellList<E> ys) throws CellListException;
	List<Pair<E,E>> getPairsDetail(DefaultCellList<E> xs) throws CellListException;
	List<Pair<E,E>> getPairsDetail(PuntualCellList<E> xs) throws CellListException;
}
