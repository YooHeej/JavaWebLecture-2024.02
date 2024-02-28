package dog;

import java.util.List;

import dog.entity.Dog;


public interface DogService {

	public static final int COUNT_PER_PAGE = 10;
	
	List<Dog> getDogList(int page, String field, String query);
	
	Dog getDog(int dogId);
	
	int getDogCount(String field, String query);
	
	void insertDog(Dog dog);
	
	void updateBoard(Dog dog);
}
