package dog;

import java.util.List;

import dog.dao.DogDao;
import dog.entity.Dog;

public class DogServiceImpl implements DogService {

	private DogDao dDao = new DogDao();
	
	@Override
	public List<Dog> getDogList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<Dog> list = dDao.getDogList(field, query, COUNT_PER_PAGE, offset);
		return list;
	}
	

	@Override
	public Dog getDog(int dogId) {
		return dDao.getDog(dogId);
	}

	@Override
	public int getDogCount(String field, String query) {
		return dDao.getDogCount(field, query);
	}

	@Override
	public void insertDog(Dog dog) {
		dDao.insertDog(dog);
		
	}


	@Override
	public void updateBoard(Dog dog) {
		dDao.updateDog(dog);
		
	}

}
