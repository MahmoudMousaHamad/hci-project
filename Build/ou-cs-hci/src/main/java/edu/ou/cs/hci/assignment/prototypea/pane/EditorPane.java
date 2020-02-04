//******************************************************************************
// Copyright (C) 2019 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Jan 28 09:28:34 2020 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20190203 [weaver]:	Original file.
// 20190220 [weaver]:	Adapted from swingmvc to fxmvc.
//
//******************************************************************************
//
//******************************************************************************

package edu.ou.cs.hci.assignment.prototypea.pane;

//import java.lang.*;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;
import edu.ou.cs.hci.assignment.prototypea.Controller;
import edu.ou.cs.hci.resources.Resources;

//******************************************************************************

/**
 * The <CODE>EditorPane</CODE> class.
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class EditorPane extends AbstractPane
{
	//**********************************************************************
	// Private Class Members
	//**********************************************************************

	private static final String	NAME = "Editor";
	private static final String	HINT = "Movie Metadata Editor";

	//**********************************************************************
	// Private Class Members (Effects)
	//**********************************************************************

	private static final Font		FONT_LARGE =
		Font.font("Serif", FontPosture.ITALIC, 24.0);

	private static final Font		FONT_SMALL =
		Font.font("Serif", FontPosture.ITALIC, 18.0);

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// Layout (a few widgets)
	private Slider					slider;

	private Spinner<Integer>		spinner;

	private TextField				textField;

	// Labels
	private Label 					directorLabel, yearLabel, ratingLabel,
									runtimeLabel, averageReviewLabel, numberOfReviewsLabel, isColorLabel, 
									isAnimatedLabel, actionGenreLabel, comedyGenreLabel, documentaryGenreLabel, 
									dramaGenreLabel, fantasyGenreLabel, horrorGenreLabel, romanceGenreLabel, 
									scifiGenreLabel, thrillerGenreLabel, westernGenreLabel, pictureAwardLabel, 
									directingAwardLabel, cinematographyAwardLabel, actingAwardLabel, 
									usernameLabel;

	// Text fields
	private TextField 				yearTF, averageRatingTF, posterPathTF;

	// Checkboxes
	private JCheckBox 				isColorCheckBox, isAnimatedCheckBox, actionGenreCheckBox, comedyGenreCheckBox, documentaryGenreCheckBox, 
									dramaGenreCheckBox, fantasyGenreCheckBox, horrorGenreCheckBox, romanceGenreCheckBox, 
									scifiGenreCheckBox, thrillerGenreCheckBox, westernGenreCheckBox, pictureAwardCheckBox, 
									directingAwardCheckBox, cinematographyAwardCheckBox, actingAwardCheckBox;

	// Spinner
	private Spinner<Integer>		numberOfReviewsSpinner;

	// Sliders
	private Slider					runtimeSlider;

	// Choice Boxes
	private ChoiceBox				ratingChoiceBox;

	// Text Areas
	private TextArea				summaryTextArea;

	// Texts
	private Text					commentText, movieTitleText, summaryHeadlineText, commentsHeadlineText;

	// Buttons
	private Button					selectPosterButton;

	// Images
	private ImageView					moviePosterImageView, userProfileImageView;

	// Handlers
	private final ActionHandler	actionHandler;

	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public EditorPane(Controller controller)
	{
		super(controller, NAME, HINT);

		actionHandler = new ActionHandler();

		setBase(buildPane());
	}

	//**********************************************************************
	// Public Methods (Controller)
	//**********************************************************************

	// The controller calls this method when it adds a view.
	// Set up the nodes in the view with data accessed through the controller.
	public void	initialize()
	{
		// Widget Gallery, Slider
		slider.setValue((Double)controller.get("myDouble"));

		// Widget Gallery, Spinner
		spinner.getValueFactory().setValue((Integer)controller.get("myInt"));

		// Widget Gallery, Text Field
		textField.setText((String)controller.get("myString"));

		// Init labels
		directorLabel = 			new Label("Director");
		yearLabel = 				new Label("Year");
		ratingLabel = 				new Label("Rating");
		runtimeLabel = 				new Label("Runtime");
		averageReviewLabel = 		new Label("Average Review");
		numberOfReviewsLabel = 		new Label("Number of Reviews");
		isColorLabel = 				new Label("Colored");
		isAnimatedLabel = 			new Label("Animated");
		actionGenreLabel = 			new Label("Action");
		comedyGenreLabel = 			new Label("Comedy");
		documentaryGenreLabel =	 	new Label("Documentary");
		dramaGenreLabel = 			new Label("Drama");
		fantasyGenreLabel = 		new Label("Fantasy");
		horrorGenreLabel = 			new Label("Horror");
		romanceGenreLabel = 		new Label("Romance");
		scifiGenreLabel = 			new Label("Sci-Fi");
		thrillerGenreLabel = 		new Label("Thriller");
		westernGenreLabel = 		new Label("Western");
		pictureAwardLabel = 		new Label("Picture Award");
		directingAwardLabel = 		new Label("Driecting Award");
		cinematographyAwardLabel = 	new Label("Cinematography Award");
		actingAwardLabel = 			new Label("Acting Award");
		usernameLabel = 			new Label("Username");

		// Init Text fields
		yearTF = new TextField();
		yearTF.setText((String) controller.get("year"));

		averageRatingTF = new TextField();
		averageRatingTF.setText((String) controller.get("average_review_score"));

		posterPathTF = new TextField();
		posterPathTF.setText((String) controller.get("poster_image_path"));

		// Checkboxes
		isColorCheckBox = new CheckBox("Colored");
		isColorCheckBox.setSelected((Boolean) controller.get("is_color"));

		isAnimatedCheckBox = new CheckBox("Animated");
		isAnimatedCheckBox.setSelected((Boolean) controller.get("is_animated"));

		actionGenreCheckBox = new CheckBox("Action");
		actionGenreCheckBox.setSelected((Boolean) controller.get("genre_action"));

		comedyGenreCheckBox = new CheckBox("Comedy");
		comedyGenreCheckBox.setSelected((Boolean) controller.get("genre_comedy"));

		documentaryGenreCheckBox = new CheckBox("Documentary");
		documentaryGenreCheckBox.setSelected((Boolean) controller.get("genre_documnetary"));

		dramaGenreCheckBox = new CheckBox("Drama");
		dramaGenreCheckBox.setSelected((Boolean) controller.get("genre_drama"));

		fantasyGenreCheckBox = new CheckBox("Fantasy");
		fantasyGenreCheckBox.setSelected((Boolean) controller.get("genre_fantasy"));

		horrorGenreCheckBox = new CheckBox("Horror");
		horrorGenreCheckBox.setSelected((Boolean) controller.get("genre_horror"));

		romanceGenreCheckBox = new CheckBox("Romance");
		romanceGenreCheckBox.setSelected((Boolean) controller.get("genre_romance"));

		scifiGenreCheckBox = new CheckBox("Sci-Fi");
		scifiGenreCheckBox.setSelected((Boolean) controller.get("genre_sci-fi"));

		thrillerGenreCheckBox = new CheckBox("Thriller");
		thrillerGenreCheckBox.setSelected((Boolean) controller.get("genre_thriller"));

		westernGenreCheckBox = new CheckBox("Western");
		westernGenreCheckBox.setSelected((Boolean) controller.get("genre_western"));

		pictureAwardCheckBox = new CheckBox("Picture Award");
		pictureAwardCheckBox.setSelected((Boolean) controller.get("award_picture"));

		directingAwardCheckBox = new CheckBox("Directing Award");
		directingAwardCheckBox.setSelected((Boolean) controller.get("award_directing"));

		cinematographyAwardCheckBox = new CheckBox("Cinematography Award");
		cinematographyAwardCheckBox.setSelected((Boolean) controller.get("award_cinematography"));

		actingAwardCheckBox = new CheckBox("Acting Award");
		actingAwardCheckBox.setSelected((Boolean) controller.get("award_acting"));

		// Init Spinner
		numberOfReviewsSpinner = new Spinner<Integer>();
		numberOfReviewsSpinner.getValueFactory().setValue((Integer) controller.get("number_of_reviews"));

		// Init Sliders
		runtimeSlider = new Slider();
		runtimeSlider.setValue((Double) controller.get("runtime"));

		// Init Choice Boxes
		ratingChoiceBox = new ChoiceBox();
		ratingChoiceBox.getItems().add("PG");
		ratingChoiceBox.getItems().add("R");
		ratingChoiceBox.getItems().add("PG-13");
		ratingChoiceBox.getItems().add("G");
		ratingChoiceBox.getSelectionModel().select((Integer) controller.get("rating"))

		// Init Text Areas
		summaryTextArea = new TextArea();
		summaryTextArea.setText((String) controller.get("summary"));

		// Init Texts
		commentText = new Text("This is a comment.");
		commentText.setFont(FONT_SMALL);

		movieTitleText = new Text("Movie Title");
		movieTitleText.setFont(FONT_LARGE);

		summaryHeadlineText = new Text("Summary");
		summaryHeadlineText.setFont(FONT_LARGE);
		
		commentsHeadlineText = new Text("Comments");
		commentsHeadlineText.setFont(FONT_LARGE);

		// Init Buttons
		selectPosterButton = new Button("Select Poster");

		// Init ImageViews
		FileInputStream posterInput = new FileInputStream(RSRC + "/assignment/movie-poster.jpg");
		FileInputStream userProfileImageInput = new FileInputStream(RSRC+ "/assignment/profile-image.png");

		Image moviePosterImage = new Image(posterInput);
		Image userProfileImage = new Image(userProfileImageInput);

		moviePosterImageView = new ImageView(moviePosterImage);
		userProfileImageView = new ImageView(userProfileImage);
	}

	// The controller calls this method when it removes a view.
	// Unregister event and property listeners for the nodes in the view.
	public void	terminate()
	{
		// Widget Gallery, Slider
		slider.valueProperty().removeListener(this::changeDecimal);

		// Widget Gallery, Spinner
		spinner.valueProperty().removeListener(this::changeInteger);

		// Widget Gallery, Text Field
		textField.setOnAction(null);

		// Text Fields
		yearTF.setOnAction(null);
		averageRatingTF.setOnAction(null);
		posterPathTF.setOnAction(null);

		// CheckBox
		isColorCheckBox.valueProperty().removeListener(this::changeBoolean);
		isAnimatedCheckBox.valueProperty().removeListener(this::changeBoolean);
		actionGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		comedyGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		documentaryGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		dramaGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		fantasyGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		horrorGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		romanceGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		scifiGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		thrillerGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		westernGenreCheckBox.valueProperty().removeListener(this::changeBoolean);
		pictureAwardCheckBox.valueProperty().removeListener(this::changeBoolean);
		directingAwardCheckBox.valueProperty().removeListener(this::changeBoolean);
		cinematographyAwardCheckBox.valueProperty().removeListener(this::changeBoolean);
		actingAwardCheckBox.valueProperty().removeListener(this::changeBoolean);

		// Spinner
		numberOfReviewsSpinner.valueProperty().removeListener(this::changeInteger);

		// Slider
		runtimeSlider.valueProperty().removeListener(this::changeDecimal);

		// ChoiceBoxes
		ratingChoiceBox.valueProperty().removeListener(this::changeBoolean);

		// Text Areas
		summaryTextArea.setOnAction(null);

	}

	// The controller calls this method whenever something changes in the model.
	// Update the nodes in the view to reflect the change.
	public void	update(String key, Object value)
	{
		//System.out.println("update " + key + " to " + value);

		if ("myDouble".equals(key))
		{
			slider.setValue((Double)value);
		}
		else if ("myInt".equals(key))
		{
			spinner.getValueFactory().setValue((Integer)value);
		}
		else if ("myString".equals(key))
		{
			textField.setText((String)value);
		}

		// if ("director".equals(key))
		// {
		// 	component.setValue((Boolean) value);
		// }
		// else if ("title".equals(key))
		// {
		// 	component.setValue((Boolean) value);
		// }
		if ("summary".equals(key))
		{
			summaryTextArea.setText((String) value);
		}
		// else if ("comment_body".equals(key))
		// {
		// 	component.setValue((Boolean) value);
		// }
		// else if ("user_name".equals(key))
		// {
		// 	component.setValue((Boolean) value);
		// }
		else if ("poster_image_path".equals(key))
		{
			posterPathTF.setText((String) value);
		}
		else if ("average_review_score".equals(key))
		{
			averageRatingTF.setText((String) value);
		}
		// else if ("award_picture".equals(key))
		// {
		// 	component.setValue((Boolean) value);
		// }
		else if ("award_directing".equals(key))
		{
			directingAwardCheckBox.setSelected((Boolean) value);
		}
		else if ("award_cinematography".equals(key))
		{
			cinematographyAwardCheckBox.setSelected((Boolean) value);
		}
		else if ("award_acting".equals(key))
		{
			actingAwardCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_action".equals(key))
		{
			actingAwardCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_comedy".equals(key))
		{
			comedyGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_drama".equals(key))
		{
			dramaGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_documentary".equals(key))
		{
			documentaryGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_drama".equals(key))
		{
			dramaGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_fantasy".equals(key))
		{
			fantasyGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_horror".equals(key))
		{
			horrorGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_romance".equals(key))
		{
			romanceGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_sci-fi".equals(key))
		{
			scifiGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_thriller".equals(key))
		{
			thrillerGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("genre_western".equals(key))
		{
			westernGenreCheckBox.setSelected((Boolean) value);
		}
		else if ("is_animated".equals(key))
		{
			isAnimatedCheckBox.setSelected((Boolean) value);
		}
		else if ("is_color".equals(key))
		{
			isColorCheckBox.setSelected((Boolean) value);
		}
		else if ("number_of_reviews".equals(key))
		{
			numberOfReviewsSpinner.getValueFactory().setValue((Integer) value);
		}
		else if ("rating".equals(key))
		{
			ratingChoiceBox.getSelectionModel().select((Integer) value);
		}
		else if ("runtime".equals(key))
		{
			runtimeSlider.setValue((Double) value);
		}
		else if ("year".equals(key))
		{
			yearTF.setText((String) value);
		}
	}

	//**********************************************************************
	// Private Methods (Layout)
	//**********************************************************************

	private Pane	buildPane()
	{
		// Layout the widgets in a vertical flow with small gaps between them.
		FlowPane	pane = new FlowPane(Orientation.VERTICAL, 8.0, 8.0);

		pane.setAlignment(Pos.TOP_LEFT);

		pane.getChildren().add(createSlider());
		pane.getChildren().add(createSpinner());
		pane.getChildren().add(createTextField());

		return pane;
	}

	//**********************************************************************
	// Private Methods (Widget Pane Creators)
	//**********************************************************************

	// Create a pane with a slider for the gallery. The progress bar and
	// slider show the same value from the model, so are synchronized.
	private Pane	createSlider()
	{
		slider = new Slider(0.0, 100.0, 0.0);

		slider.setOrientation(Orientation.HORIZONTAL);
		slider.setMajorTickUnit(20.0);
		slider.setMinorTickCount(4);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);

		slider.valueProperty().addListener(this::changeDecimal);

		return createTitledPane(slider, "Slider");
	}

	// Create a pane with a spinner for the gallery. The progress bar,
	// slider, and spinner show the same value from the model, so stay synced.
	private Pane	createSpinner()
	{
		spinner = new Spinner<Integer>(0, 100, 0, 1);

		spinner.setEditable(true);
		spinner.getEditor().setPrefColumnCount(4);

		spinner.valueProperty().addListener(this::changeInteger);

		return createTitledPane(spinner, "Spinner");
	}

	// Create a pane with a text field for the gallery.
	private Pane	createTextField()
	{
		textField = new TextField();

		textField.setPrefColumnCount(6);

		textField.setOnAction(actionHandler);

		return createTitledPane(textField, "Text Field");
	}

	//**********************************************************************
	// Private Methods (Property Change Handlers)
	//**********************************************************************

	private void	changeDecimal(ObservableValue<? extends Number> observable,
								  Number oldValue, Number newValue)
	{
		if (observable == slider.valueProperty())
			controller.set("myDouble", newValue);
	}

	private void	changeInteger(ObservableValue<? extends Number> observable,
								  Number oldValue, Number newValue)
	{
		if (observable == spinner.valueProperty())
			controller.set("myInt", newValue);
	}

	//**********************************************************************
	// Inner Classes (Event Handlers)
	//**********************************************************************

	private final class ActionHandler
		implements EventHandler<ActionEvent>
	{
		public void	handle(ActionEvent e)
		{
			Object	source = e.getSource();

			if (source == textField)
				controller.set("myString", textField.getText());
		}
	}
}

//******************************************************************************
