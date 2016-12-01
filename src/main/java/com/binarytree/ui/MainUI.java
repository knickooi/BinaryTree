package com.binarytree.ui;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.binarytree.util.BinaryTree;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
//@Widgetset("org.vaadin.hezamu.canvas.CanvasWidgetset")
@SuppressWarnings("serial")
public class MainUI extends UI {
	
	private TextArea area;
	private TextArea searchArea;
	private Label maxLabel;
	private Label minLabel;

	@Override
	protected void init(VaadinRequest request) {
		System.out.println("Here");
		
		final VerticalLayout root = new VerticalLayout();
    	root.setSizeFull();
    	root.setMargin(true);
    	root.setSpacing(true);
    	setContent(root);
    	
    	root.addComponent(area = new TextArea("Binary Tree"));
    	
    	final HorizontalLayout info = new HorizontalLayout();
    	info.addComponent(maxLabel = new Label());
    	info.addComponent(new Label("   "));
    	info.addComponent(minLabel = new Label());
    	info.setSpacing(true);
    	
    	root.addComponent(info);
    	
    	root.addComponent(searchArea = new TextArea("Binary Search Tree"));
    	
    	area.setSizeFull();
    	searchArea.setSizeFull();
//    	area.setReadOnly(true);
    	
    	Styles styles = Page.getCurrent().getStyles();
        // inject the new font size as a style. We need .v-app to override Vaadin's default styles here
        styles.add(".v-app textarea.v-textarea { font-family: monospace; }");
		
    	drawTree();
	}
	
	private void drawTree() {
		Random random = new Random();
		List<Integer> sample = random.ints(20, 0, 50).boxed().collect(Collectors.toList());
		
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.setData(sample);
		
		System.out.println(tree.toString());
		
		area.setValue(tree.toString());
		
		maxLabel.setValue("Max value: " + tree.getMax().getValue());
		minLabel.setValue("Min value: " + tree.getMin().getValue());
		
		BinaryTree<Integer> searchTree = new BinaryTree<>();
		searchTree.setDataSorted(sample);
		
		searchArea.setValue(searchTree.toString());
	}

}
