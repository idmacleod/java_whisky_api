package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {}

	@Test
	public void canFindWhiskiesByYear() {
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByYear(1995);
		assertEquals("The Macallan Anniversary Malt", foundWhiskies.get(0).getName());
	}

	@Test
	public void canFindDistilleriesByRegion() {
		List<Distillery> foundDistilleries = distilleryRepository.findDistilleriesByRegion("Island");
		assertEquals(3, foundDistilleries.size());
	}

	@Test
	public void canFindWhiskiesByDistilleryIdAndAge() {
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByDistilleryIdAndAge(1L, 15);
		assertEquals("The Glendronach Revival", foundWhiskies.get(0).getName());
	}

	@Test
	public void canFindWhiskiesByRegion(){
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskiesByDistilleryRegion("Islay");
		assertEquals(2, foundWhiskies.size());
	}

	@Test
	public void canFindDistilleriesByWhiskiesAge() {
		List<Distillery> foundDistilleries = distilleryRepository.findDistilleriesByWhiskiesAge(12);
		assertEquals(6, foundDistilleries.size());
	}

}
