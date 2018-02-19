package main.java;


// SortDemo.java     
//                                              
// Demonstrates various sorting algorithms on an array generated from a
// text area by StringTokenizer
//
// Assumptions: 
// input is a sequence of n numbers a0, a1, a2 ... 
// n is the length of an array
// a0, a1, ... are the sorting keys of the array entries of type Item
// numbers are separated by white space 

import java.awt.*;
import java.applet.Applet;
import java.util.*;   // for class StringTokenizer
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;

public class SortDemo extends Applet {

		SortDemoData data = new SortDemoData();
        private TextArea _input, _output;  
        private Button _startBtn;
        private Choice _choiceBtn;
        private Panel _p1, _p2, _p3;
        private String _helpStr = 
                "Please enter an int value or a string of int numbers \n"
                + "If you only entered one number a randomized \n"
                + "array will be created.  \n"
                + "If you give a string of numbers these will be used for sorting.\n\n"
                + "You can choose the sorting algorithm. \n";
                               
        
        // setup the graphical user interface components
        public void init() {
                setSize(700, 600);
                setLayout(new FlowLayout(FlowLayout.LEFT));
                setFont(new Font("Courier", Font.PLAIN, 18));
        
                _p1 = new Panel();
                _p2 = new Panel();
                _p3 = new Panel();
                
                _p1.setLayout(new FlowLayout(FlowLayout.LEFT));
                
                _input = new TextArea("63 24 12 53 72 18 44 80 ", 5, 60);
                _p1.add(_input);          // put input on panel
        
                _p2.setLayout(new FlowLayout(FlowLayout.LEFT));
               
                                
                _choiceBtn = new Choice();
                _choiceBtn.setFont(new Font("Times", Font.PLAIN, 18));
                _choiceBtn.addItem("  Bubble Sort  ");
                _choiceBtn.addItem("  Selection Sort  ");
                _choiceBtn.addItem("  Insertion Sort  ");
                _choiceBtn.addItem("  Merge Sort  ");
                _choiceBtn.addItem("  Quick Sort  ");
                _choiceBtn.addItem("  Heap Sort  ");
                _choiceBtn.setSize(2000,500);
                System.out.println(_choiceBtn.getSize().toString());
                _p2.add(_choiceBtn);
            
                _startBtn = new Button("  Start  ");
                _startBtn.setFont(new Font("Times", Font.PLAIN, 18));	
                _startBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                runDemo();
                        }
                });
                _p2.add(_startBtn);
                
                _p3.setLayout(new FlowLayout(FlowLayout.LEFT));
                _output = new TextArea(_helpStr, 15, 60);
                _p3.add(_output);
                                    
                add(_p1);
                add(_p2);
                add(_p3);
        }
        
        private void readArray(){
        	 String _inputStr = _input.getText();
        	 data.initializeArray(_inputStr);
        	 
        }
        
             
        // process user's action on the input text field
        public void displayHelp() {
                _output.setText(_helpStr); 
        }
        
        public void runDemo() {  
                try {
                        _output.setText("");
                        long startTime = System.currentTimeMillis();
                        readArray();              
                        long endTime = System.currentTimeMillis();
                        long runTime = endTime - startTime; 
                        String initTime = new String(
                                "Time to create array: "
                                + Long.toString(runTime) + " ms\n");  
                        _output.setText(initTime);  
                        
                        int choice = _choiceBtn.getSelectedIndex();
                        
                        StringBuffer _outputBuf = data.runAlgo(choice);
                        String choiceStr = _choiceBtn.getSelectedItem();
                        
                        endTime = System.currentTimeMillis();
                        runTime = endTime - startTime;        
                            
                        // Construct the sorting time in a String
                        String sortTime = new String(
                                "Time needed for " 
                                + choiceStr + ": "
                                + Long.toString(runTime) + " ms\n");    
                        _output.append(sortTime);    
                        
                        
                        _output.setText(initTime + sortTime + "\r" 
                        		+ _outputBuf.toString()); 
                        		
                // check routine
                
                 } catch (NoSuchElementException e) {
       			 System.out.println("Not enough numbers.");
	       	     } catch (NumberFormatException e) {
	       	    	 System.out.println("Please only use integer values. ");
	       	     } catch (NegativeArraySizeException e) {
	       	    	 System.out.println("Negative Array Size.");
	       	     }      
        }        		
}


