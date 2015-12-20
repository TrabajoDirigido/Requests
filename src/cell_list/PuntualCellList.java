package cell_list;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class PuntualCellList<E> implements ExcelCellList<E>{
	
	private E p;
	
	public PuntualCellList(E p){
		this.p = p;
	}
	
	public E getP(){
		return p;
	}

	@Override
	public List<Pair<E, E>> getPairs(ExcelCellList<E> ys) throws CellListException {
		return ys.getPairsDetail(this);
	}

	@Override
	public List<Pair<E, E>> getPairsDetail(DefaultCellList<E> xs) {
		//System.out.println("first is DefaultCellList, second is PuntualCellList");
		List<Pair<E, E>> list = new ArrayList<>();
		
		for(E x :xs.getPoints()){
			list.add(new Pair<>(x, this.p));
		}	
		
		return list;
	}

	@Override
	public List<Pair<E, E>> getPairsDetail(PuntualCellList<E> xs) {
		//System.out.println("first is PuntualCellList, second is PuntualCellList");
		
		List<Pair<E, E>> list = new ArrayList<>();
		list.add(new Pair<>(xs.p,this.p));
		
		return list;
	}

}
