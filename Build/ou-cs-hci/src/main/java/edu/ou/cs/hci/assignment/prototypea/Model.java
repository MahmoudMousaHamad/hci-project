//******************************************************************************
// Copyright (C) 2019-2020 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Wed Feb 20 19:34:56 2019 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20190203 [weaver]:	Original file.
// 20190220 [weaver]:	Adapted from swingmvc to fxmvc.
//
//******************************************************************************
//
//******************************************************************************

package edu.ou.cs.hci.assignment.prototypea;

//import java.lang.*;
import java.util.HashMap;
import javafx.application.Platform;

//******************************************************************************

/**
 * The <CODE>Model</CODE> class.
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Model
{
	//**********************************************************************
	// Private Members
	//**********************************************************************

	// Master of the program, manager of the data, mediator of all updates
	private final Controller				controller;

	// Easy, extensible way to store multiple simple, independent parameters
	private final HashMap<String, Object>	properties;

	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public Model(Controller controller)
	{
		this.controller = controller;

		properties = new HashMap<String, Object>();

		// Parameters accessed and/or modified by EditorPane controls
		properties.put("director",						"director_name");
		properties.put("title",							"title");
		properties.put("summary",						"Summary summary summary summary summary summary summary summary ");
		properties.put("comment_body",					"Great movie!");
		properties.put("user_name",						"Mahmoud Mousa Hamad");
		properties.put("poster_image_path",				"path/to/image.jpg");
		properties.put("average_review_score",			10.0);
		properties.put("award_picture",					Boolean.FALSE);
		properties.put("award_directing",				Boolean.FALSE);
		properties.put("award_cinematography",			Boolean.FALSE);
		properties.put("award_acting",					Boolean.FALSE);
		properties.put("genre_action",					Boolean.FALSE);
		properties.put("genre_comedy",					Boolean.FALSE);
		properties.put("genre_drama",					Boolean.FALSE);
		properties.put("genre_documentary",				Boolean.FALSE);
		properties.put("genre_drama",					Boolean.FALSE);
		properties.put("genre_fantasy",					Boolean.FALSE);
		properties.put("genre_horror",					Boolean.FALSE);
		properties.put("genre_romance",					Boolean.FALSE);
		properties.put("genre_sci-fi",					Boolean.FALSE);
		properties.put("genre_thriller",				Boolean.FALSE);
		properties.put("genre_western",					Boolean.FALSE);
		properties.put("is_animated",					Boolean.FALSE);
		properties.put("is_color",						Boolean.FALSE);
		properties.put("number_of_reviews",				10);
		properties.put("rating",						1);
		properties.put("runtime",						250);
		properties.put("year",							2010);
	}

	//**********************************************************************
	// Public Methods (Controller)
	//**********************************************************************

	public Object	getValue(String key)
	{
		return properties.get(key);
	}

	public void	setValue(String key, Object value)
	{
		if (properties.containsKey(key) &&
			properties.get(key).equals(value))
		{
			System.out.println("  model: value not changed");
			return;
		}

		Platform.runLater(new Updater(key, value));
	}

	public void	trigger(String name)
	{
		System.out.println("  model: (not!) calculating function: " + name);
	}

	//**********************************************************************
	// Inner Classes
	//**********************************************************************

	private class Updater
		implements Runnable
	{
		private final String	key;
		private final Object	value;

		public Updater(String key, Object value)
		{
			this.key = key;
			this.value = value;
		}

		public void	run()
		{
			properties.put(key, value);
			controller.update(key, value);
		}
	}
}

//******************************************************************************
