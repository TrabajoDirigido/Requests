package cell_list;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class DefaultCellList<E> implements ExcelCellList<E>{
	
	private List<E> points;
	
	public DefaultCellList(List<E> points){
		this.points = points;
	}
	
	public List<E> getPoints(){
		return this.points;
	}
	
	@Override
	public List<Pair<E, E>> getPairs(ExcelCellList<E> secondList) throws CellListException {
		return secondList.getPairsDetail(this);
	}

	@Override
	public List<Pair<E, E>> getPairsDetail(DefaultCellList<E> xs) throws CellListException {
		//System.out.println("first is DefaultCellList, second is DefaultCellList");
		
		List<Pair<E, E>> list = new ArrayList<>();
		
		if(this.points.size()!= xs.points.size()){
			throw new CellListException("DefaultCellList with different sizes");
		}
		
		for(int i=0; i<this.points.size(); i++){
			list.add(new Pair<>(xs.points.get(i), this.points.get(i)));
		}	
		
		return list;
	}

	@Override
	public List<Pair<E, E>> getPairsDetail(
			PuntualCellList<E> xs) {
		//System.out.println("first is PuntualCellList, second is DefaultCellList");

		List<Pair<E, E>> list = new ArrayList<>();
		
		for(int i=0; i<this.points.size(); i++){
			list.add(new Pair<>(xs.getP(), this.points.get(i)));
		}	
		
		return list;
	}

}
