package com.uobia.gpes.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.uobia.gpes.event.Event;

public class ReproductorTest {
    @Test
    public void shouldReproduceFromOne() {
        @SuppressWarnings("unused")
		Actor offspring = Reproductor.reproduceFromOne(createTestParent1());
        //TODO Define requirements for reproduction from one parent
        Assert.assertTrue("Offspring should be correctly created from two parents", false);
    }

	@Test
    public void shouldReproduceFromMultiple() {
        List<Actor> parents = new ArrayList<Actor>();
        parents.add(createTestParent1());
        parents.add(createTestParent2());
        @SuppressWarnings("unused")
		Actor offspring = Reproductor.reproduceFromMultiple(parents);
        //TODO Define requirements for reproduction from one parent
        Assert.assertTrue("Offspring should be correctly created from one parent", false);
    }
	
	@Test
	public void shouldFindParentsOfPartials() {
		List<Actor> candidates = createCandidateTestParents();
		List<Event> partials = createTestPartials();
		@SuppressWarnings("unused")
		List< ArrayList<Actor>> parents = Reproductor.parentsOfPartials(candidates, partials);
		//TODO Define requirements for finding parents of partials;
        Assert.assertTrue("Parents of partials should be correctly found", false);
	}

    private static Actor createTestParent1() {
        // TODO create parent actor containing rules and info
        Actor actor = Actor.create();
        return actor;
    }
    
    private static Actor createTestParent2() {
        // TODO create parent actor containing rules and info
        Actor actor = Actor.create();
        return actor;
    }
    
    private static List<Event> createTestPartials() {
    	//TODO create set of partial reproduction events which have some matches
    	List<Event> events = new ArrayList<Event>();
    	return events;
    }
    
    private static List<Actor> createCandidateTestParents() {
    	//TODO create a list of candidate parents that correspond to partial reproduction events
    	List<Actor> parents = new ArrayList<Actor>();
    	return parents;
    }
}
