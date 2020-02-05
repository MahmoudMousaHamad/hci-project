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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

import edu.ou.cs.hci.assignment.prototypea.Controller;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

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
	private CheckBox 				isColorCheckBox, isAnimatedCheckBox, actionGenreCheckBox, comedyGenreCheckBox, documentaryGenreCheckBox, 
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

	public EditorPane(final Controller controller) {
		super(controller, NAME, HINT);

		actionHandler = new ActionHandler();

		setBase(buildPane());
	}

	// **********************************************************************
	// Public Methods (Controller)
	// **********************************************************************

	// The controller calls this method when it adds a view.
	// Set up the nodes in the view with data accessed through the controller.
	public void initialize() {
		// Widget Gallery, Slider
		slider.setValue((Double) controller.get("myDouble"));
		// Widget Gallery, Spinner
		spinner.getValueFactory().setValue((Integer) controller.get("myInt"));
		// Widget Gallery, Text Field
		textField.setText((String) controller.get("myString"));
		// Init Text fields
		yearTF.setText(Integer.toString((int) controller.get("year")));
		averageRatingTF.setText(Double.toString((Double) controller.get("average_review_score")));
		posterPathTF.setText((String) controller.get("poster_image_path"));
		// Checkboxes
		isColorCheckBox.setSelected((Boolean) controller.get("is_color"));	
		isAnimatedCheckBox.setSelected((Boolean) controller.get("is_animated"));	
		actionGenreCheckBox.setSelected((Boolean) controller.get("genre_action"));	
		comedyGenreCheckBox.setSelected((Boolean) controller.get("genre_comedy"));	
		documentaryGenreCheckBox.setSelected((Boolean) controller.get("genre_documentary"));	
		dramaGenreCheckBox.setSelected((Boolean) controller.get("genre_drama"));	
		fantasyGenreCheckBox.setSelected((Boolean) controller.get("genre_fantasy"));	
		horrorGenreCheckBox.setSelected((Boolean) controller.get("genre_horror"));	
		romanceGenreCheckBox.setSelected((Boolean) controller.get("genre_romance"));	
		scifiGenreCheckBox.setSelected((Boolean) controller.get("genre_sci-fi"));	
		thrillerGenreCheckBox.setSelected((Boolean) controller.get("genre_thriller"));	
		westernGenreCheckBox.setSelected((Boolean) controller.get("genre_western"));	
		pictureAwardCheckBox.setSelected((Boolean) controller.get("award_picture"));	
		directingAwardCheckBox.setSelected((Boolean) controller.get("award_directing"));	
		cinematographyAwardCheckBox.setSelected((Boolean) controller.get("award_cinematography"));	
		actingAwardCheckBox.setSelected((Boolean) controller.get("award_acting"));
		// Init Spinner
		numberOfReviewsSpinner.getValueFactory().setValue((Integer) controller.get("number_of_reviews"));
		// Init Sliders
		runtimeSlider.setValue((Double) controller.get("runtime"));
		// Init Choice Boxes
		ratingChoiceBox.getItems().add("PG");
		ratingChoiceBox.getItems().add("R");
		ratingChoiceBox.getItems().add("PG-13");
		ratingChoiceBox.getItems().add("G");
		ratingChoiceBox.getSelectionModel().select((Integer) controller.get("rating"));
		// Init Text Areas
		summaryTextArea.setText((String) controller.get("summary"));
		// Init Texts
		commentText.setFont(FONT_SMALL);
		movieTitleText.setFont(FONT_LARGE);
		summaryHeadlineText.setFont(FONT_LARGE);
		commentsHeadlineText.setFont(FONT_LARGE);
	}

	// The controller calls this method when it removes a view.
	// Unregister event and property listeners for the nodes in the view.
	public void terminate() {
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
		isColorCheckBox.selectedProperty().removeListener(this::changeBoolean);
		isAnimatedCheckBox.selectedProperty().removeListener(this::changeBoolean);
		actionGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		comedyGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		documentaryGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		dramaGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		fantasyGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		horrorGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		romanceGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		scifiGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		thrillerGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		westernGenreCheckBox.selectedProperty().removeListener(this::changeBoolean);
		pictureAwardCheckBox.selectedProperty().removeListener(this::changeBoolean);
		directingAwardCheckBox.selectedProperty().removeListener(this::changeBoolean);
		cinematographyAwardCheckBox.selectedProperty().removeListener(this::changeBoolean);
		actingAwardCheckBox.selectedProperty().removeListener(this::changeBoolean);

		// Spinner
		numberOfReviewsSpinner.valueProperty().removeListener(this::changeInteger);

		// Slider
		runtimeSlider.valueProperty().removeListener(this::changeDecimal);

		// ChoiceBoxes
		ratingChoiceBox.getSelectionModel().selectedIndexProperty().removeListener(this::changeInteger);

		// Text Areas
		summaryTextArea.textProperty().removeListener(this::changeString);

	}

	// The controller calls this method whenever something changes in the model.
	// Update the nodes in the view to reflect the change.
	public void update(final String key, final Object value) {
		// System.out.println("update " + key + " to " + value);

		if ("myDouble".equals(key)) {
			slider.setValue((Double) value);
		} else if ("myInt".equals(key)) {
			spinner.getValueFactory().setValue((Integer) value);
		} else if ("myString".equals(key)) {
			textField.setText((String) value);
		}

		// if ("director".equals(key))
		// {
		// component.setValue((Boolean) value);
		// }
		// else if ("title".equals(key))
		// {
		// component.setValue((Boolean) value);
		// }
		if ("summary".equals(key)) {
			summaryTextArea.setText((String) value);
		}
		// else if ("comment_body".equals(key))
		// {
		// component.setValue((Boolean) value);
		// }
		// else if ("user_name".equals(key))
		// {
		// component.setValue((Boolean) value);
		// }
		else if ("poster_image_path".equals(key)) {
			posterPathTF.setText((String) value);
		} else if ("average_review_score".equals(key)) {
			averageRatingTF.setText((String) value);
		}
		// else if ("award_picture".equals(key))
		// {
		// component.setValue((Boolean) value);
		// }
		else if ("award_directing".equals(key)) {
			directingAwardCheckBox.setSelected((Boolean) value);
		} else if ("award_cinematography".equals(key)) {
			cinematographyAwardCheckBox.setSelected((Boolean) value);
		} else if ("award_acting".equals(key)) {
			actingAwardCheckBox.setSelected((Boolean) value);
		} else if ("genre_action".equals(key)) {
			actingAwardCheckBox.setSelected((Boolean) value);
		} else if ("genre_comedy".equals(key)) {
			comedyGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_drama".equals(key)) {
			dramaGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_documentary".equals(key)) {
			documentaryGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_drama".equals(key)) {
			dramaGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_fantasy".equals(key)) {
			fantasyGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_horror".equals(key)) {
			horrorGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_romance".equals(key)) {
			romanceGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_sci-fi".equals(key)) {
			scifiGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_thriller".equals(key)) {
			thrillerGenreCheckBox.setSelected((Boolean) value);
		} else if ("genre_western".equals(key)) {
			westernGenreCheckBox.setSelected((Boolean) value);
		} else if ("is_animated".equals(key)) {
			isAnimatedCheckBox.setSelected((Boolean) value);
		} else if ("is_color".equals(key)) {
			isColorCheckBox.setSelected((Boolean) value);
		} else if ("number_of_reviews".equals(key)) {
			numberOfReviewsSpinner.getValueFactory().setValue((Integer) value);
		} else if ("rating".equals(key)) {
			ratingChoiceBox.getSelectionModel().select((Integer) value);
		} else if ("runtime".equals(key)) {
			runtimeSlider.setValue((Double) value);
		} else if ("year".equals(key)) {
			yearTF.setText((String) value);
		}
	}

	// **********************************************************************
	// Private Methods (Layout)
	// **********************************************************************

	private Pane buildPane() {
		initialize();
		// Layout the widgets in a vertical flow with small gaps between them.
		final FlowPane pane = new FlowPane(Orientation.VERTICAL, 8.0, 8.0);

		pane.setAlignment(Pos.TOP_LEFT);

		pane.getChildren().add(createSlider());
		pane.getChildren().add(createSpinner());
		pane.getChildren().add(createTextField());

		// Title
		pane.getChildren().add(movieTitleText);
		// Poster
		// CheckBoxes

		return pane;
	}

	// **********************************************************************
	// Private Methods (Widget Pane Creators)
	// **********************************************************************

	private Pane createStackPane(Node... nodes)
	{
		StackPane stackPane = new StackPane();
		for (Node node : nodes)
		{
			stackPane.getChildren().add(node);
		}
		return stackPane;
	}

	private Pane createCheckBoxes()
	{	
		isColorCheckBox = new CheckBox("Colored");
		isAnimatedCheckBox = new CheckBox("Animated");
		actionGenreCheckBox = new CheckBox("Action");
		comedyGenreCheckBox = new CheckBox("Comedy");
		documentaryGenreCheckBox = new CheckBox("Documentary");
		dramaGenreCheckBox = new CheckBox("Drama");
		fantasyGenreCheckBox = new CheckBox("Fantasy");
		horrorGenreCheckBox = new CheckBox("Horror");
		romanceGenreCheckBox = new CheckBox("Romance");
		scifiGenreCheckBox = new CheckBox("Sci-Fi");
		thrillerGenreCheckBox = new CheckBox("Thriller");
		westernGenreCheckBox = new CheckBox("Western");
		pictureAwardCheckBox = new CheckBox("Picture Award");
		directingAwardCheckBox = new CheckBox("Directing Award");
		cinematographyAwardCheckBox = new CheckBox("Cinematography Award");
		actingAwardCheckBox = new CheckBox("Acting Award");

		return createStackPane(isColorCheckBox, isAnimatedCheckBox, actionGenreCheckBox,
		 comedyGenreCheckBox, documentaryGenreCheckBox, dramaGenreCheckBox, fantasyGenreCheckBox, 
		 horrorGenreCheckBox, romanceGenreCheckBox, scifiGenreCheckBox, thrillerGenreCheckBox, 
		 westernGenreCheckBox, pictureAwardCheckBox, directingAwardCheckBox, 
		 cinematographyAwardCheckBox, actingAwardCheckBox);
	}

	private Pane createTextFields()
	{
		yearTF = new TextField();
		averageRatingTF = new TextField();
		posterPathTF = new TextField();

		return createStackPane(yearTF, averageRatingTF, posterPathTF);
	}

	private Pane createSpinner()
	{
		numberOfReviewsSpinner = new Spinner<Integer>(0, 100, 0, 1);
		return createStackPane(numberOfReviewsSpinner);
	}

	private Pane createSlider()
	{
		runtimeSlider = new Slider();
		return createStackPane(runtimeSlider);
	}

	private Pane createChoiceBox()
	{
		ratingChoiceBox = new ChoiceBox();
		return createStackPane(ratingChoiceBox);
	}

	private Pane createTextArea()
	{
		summaryTextArea = new TextArea();
		return createStackPane(summaryTextArea);
	}

	private void createTextsWithoutPane()
	{
		commentText = new Text("This is a comment.");
		movieTitleText = new Text("Movie Title");
		summaryHeadlineText = new Text("Summary");
		commentsHeadlineText = new Text("Comments");
	}

	private void createButtonWithoutPane()
	{
		selectPosterButton = new Button("Select Poster");
	}

	private void createImageViewsWithoutPane()
	{
		FileInputStream posterInput;
		try 
		{
			posterInput = new FileInputStream(RSRC + "/assignment/movie-poster.jpg");
			final Image moviePosterImage = new Image(posterInput);
			moviePosterImageView = new ImageView(moviePosterImage);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		FileInputStream userProfileImageInput;
		try 
		{
			userProfileImageInput = new FileInputStream(RSRC + "/assignment/profile-image.png");
			final Image userProfileImage = new Image(userProfileImageInput);
			userProfileImageView = new ImageView(userProfileImage);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	// Create a pane with a slider for the gallery. The progress bar and
	// slider show the same value from the model, so are synchronized.
	private Pane createSlider1() {
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
	private Pane createSpinner1() {
		spinner = new Spinner<Integer>(0, 100, 0, 1);

		spinner.setEditable(true);
		spinner.getEditor().setPrefColumnCount(4);

		spinner.valueProperty().addListener(this::changeInteger);

		return createTitledPane(spinner, "Spinner");
	}

	// Create a pane with a text field for the gallery.
	private Pane createTextField() {
		textField = new TextField();

		textField.setPrefColumnCount(6);

		textField.setOnAction(actionHandler);

		return createTitledPane(textField, "Text Field");
	}

	// **********************************************************************
	// Private Methods (Property Change Handlers)
	// **********************************************************************

	private void changeDecimal(final ObservableValue<? extends Number> observable, final Number oldValue,
			final Number newValue) {
		if (observable == slider.valueProperty())
			controller.set("myDouble", newValue);
		
		if (observable == runtimeSlider.valueProperty())
			controller.set("runtime", newValue);
	}

	private void changeInteger(final ObservableValue<? extends Number> observable, final Number oldValue,
			final Number newValue) {
		if (observable == spinner.valueProperty())
			controller.set("myInt", newValue);
		
		if (observable == numberOfReviewsSpinner.valueProperty())
			controller.set("number_of_reviews", newValue);
		
		if (observable == ratingChoiceBox.valueProperty())
			controller.set("rating", newValue);
	}

	private void changeBoolean(final ObservableValue<? extends Boolean> observable, final Boolean oldValue,
			final Boolean newValue) {
		if (observable.equals(spinner.valueProperty()))
			controller.set("myInt", newValue);

		if (observable.equals(isColorCheckBox.selectedProperty()))
			controller.set("is_color", newValue);

		if (observable.equals(isAnimatedCheckBox.selectedProperty()))
			controller.set("is_animated", newValue);

		if (observable.equals(actionGenreCheckBox.selectedProperty()))
			controller.set("genre_action", newValue);
			
		if (observable.equals(comedyGenreCheckBox.selectedProperty()))
			controller.set("genre_comedy", newValue);

		if (observable.equals(documentaryGenreCheckBox.selectedProperty()))
			controller.set("genre_documentary", newValue);
			
		if (observable.equals(dramaGenreCheckBox.selectedProperty()))
			controller.set("genre_drama", newValue);
			
		if (observable.equals(fantasyGenreCheckBox.selectedProperty()))
			controller.set("genre_fantasy", newValue);

		if (observable.equals(horrorGenreCheckBox.selectedProperty()))
			controller.set("genre_horror", newValue);
			
		if (observable.equals(romanceGenreCheckBox.selectedProperty()))
			controller.set("genre_romance", newValue);
			
		if (observable.equals(scifiGenreCheckBox.selectedProperty()))
			controller.set("genre_sci-fi", newValue);
			
		if (observable.equals(thrillerGenreCheckBox.selectedProperty()))
			controller.set("genre_thriller", newValue);
			
		if (observable.equals(westernGenreCheckBox.selectedProperty()))
			controller.set("genre_western", newValue);
			
		if (observable.equals(pictureAwardCheckBox.selectedProperty()))
			controller.set("award_picture", newValue);
			
		if (observable.equals(directingAwardCheckBox.selectedProperty()))
			controller.set("award_directing", newValue);
			
		if (observable.equals(cinematographyAwardCheckBox.selectedProperty()))
			controller.set("award_cinematography", newValue);
			
		if (observable.equals(actingAwardCheckBox.selectedProperty()))
			controller.set("award_acting", newValue);
	}
	
	private void changeString(final ObservableValue<? extends String> observable, final String oldValue,
			final String newValue) {
		if (observable.equals(summaryTextArea.getText()))
			controller.set("summary", newValue);
	}

	// **********************************************************************
	// Inner Classes (Event Handlers)
	// **********************************************************************

	private final class ActionHandler implements EventHandler<ActionEvent> {
		public void handle(final ActionEvent e) {
			final Object source = e.getSource();

			if (source == textField)
				controller.set("myString", textField.getText());
		}
	}
}

//******************************************************************************
