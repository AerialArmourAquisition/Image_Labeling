![workflow](https://github.com/AerialArmourAquisition/Image_Labeling/actions/workflows/linux_JDK_11.yml/badge.svg)
![workflow](https://github.com/AerialArmourAquisition/Image_Labeling/actions/workflows/linux_JDK_15.yml/badge.svg)
![workflow](https://github.com/AerialArmourAquisition/Image_Labeling/actions/workflows/linux_JDK_16.yml/badge.svg)

# Image_Labelling
A labelling tool that displays satellite images and allows the user to graphically draw boxes that are converted into .txt label files for training ML algorithms


## Installation
Clone the repo and ensure you have Gradle vesrion 8.0 and Java version 16 installed.  Navigate to the directory and run:
```
gradle build
```
followed by
```
gradle run
```

## Use
To select a single image, use the `Select Img` button,  `Select Dir` allows the user to select a directory and move through all `.jpg`,`.jpeg`, and `.png` using the arrow buttons.  Hold the `ctl` key and move the mouse to move the image use the `left click` to draw a box around vehicles. Once all vehicles have been selected, click `save` to write to file with the format:

_x_position, y_position, width, height_

where all values are in the range 0 to 1, where 0 is the top left corner of the image and 1 is the extremity of the image boundaries

![screenshot](https://github.com/AerialArmourAquisition/Image_Labelling/blob/master/screenshots/Screenshot%20from%202022-01-02%2022-38-44.png)
