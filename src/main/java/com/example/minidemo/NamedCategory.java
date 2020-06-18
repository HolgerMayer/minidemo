package com.example.minidemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NamedCategory {

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String name;

	  protected NamedCategory() {}

	  public NamedCategory(String name) {
	    this.name = name;
	  }

	  @Override
	  public String toString() {
	    return String.format(
	        "NamedCategory[id=%d, name='%s']",
	        id, name);
	  }

	  public Long getId() {
	    return id;
	  }
	  
	  public void setId(long id) {
	        this.id = id;
	  }

	  public String getName() {
	    return name;
	  }
}
