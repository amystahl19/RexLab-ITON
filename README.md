# RexLab-ITON

## Description

This repository contains supplemental files and tools for the JoVE manuscript detailing the methods for inducing intraocular traumatic optic neuropathy (ITON) using a modified paintball gun system. The tools include ImageJ plug-ins, LabVIEW virtual instrument files, and recipes for preparing necessary solutions.

## Table of Contents

1. [Files Included](#files-included)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Recipes](#recipes)
5. [License](#license)
6. [Authors and Acknowledgements](#authors-and-acknowledgements)
7. [Contact Information](#contact-information)

## Files Included

### ImageJ Plug-ins

- **Counting_Array_Mac.Class or Counting_Array_PC.java**: A macro plug-in for overlaying a fixed grid to facilitate systematic sampling of optic nerve (ON) cross-sections.
- **Better_Cell_Counter_Mac.jar or Better_Cell_Counter_PC.jar**: A macro plug-in for counting living and degenerating axons separately in ON cross-sections.

### LabVIEW Virtual Instrument

- **DataAcquisition.vi**: A virtual instrument file for National Instruments LabVIEW, used for data acquisition from the pressure transducer.

### Recipes

- **Recipes For Solutions.docx**: Recipes for: 0.1M Cacodylate Buffer (pH 7.4), 2% Osmium (0.2M), Epon, and 1% Paraphenylenediamine (PPD) in 1:1 methanol and 2-propanol 

## Installation

### ImageJ Plug-ins

1. **Download ImageJ**: Ensure you have ImageJ or Fiji installed on your computer. You can download it from [here](https://imagej.nih.gov/ij/download.html).
2. **Install Plug-ins**:
   - If using a Mac, Download the `Counting_Array_Mac.class` and           `Better_Cell_Counter_Mac.jar` files from this repository.
   - If using a PC, Download the `Counting_Array_PC.java` and           `Better_Cell_Counter_PC.jar` files from this repository.
   - Copy the files to the `plugins` folder in your ImageJ installation directory.

### LabVIEW Virtual Instrument

1. **Download LabVIEW**: Ensure you have LabVIEW installed on your computer. You can find more information [here](https://www.ni.com/en-us/shop/labview.html).
2. **Install Virtual Instrument**:
   - Download the `MousePressureMeasurement.vi` file from this repository.
   - Open the file in LabVIEW.

## Usage

### ImageJ Plug-ins

1. **Counting Array**:
   - Open ImageJ or Fiji.
   - Load your ON cross-section image.
   - Run the `Counting_Array_Mac.class` or `Counting_Array_PC.java` plug-in to overlay the fixed grid.

2. **Cell Counter**:
   - Open ImageJ or Fiji.
   - Load your ON cross-section image.
   - Run the `Better_Cell_Counter_Mac.jar` or `Better_Cell_Counter_PC.jar` plug-in to count living and degenerating axons.

### LabVIEW Virtual Instrument

1. **Data Acquisition**:
   - Connect your pressure transducer to the DAQ system and computer.
   - Open `MousePressureMeasurement.vi` in LabVIEW.
   - Configure the input channels and run the virtual instrument to start data acquisition.

## Recipes

1. **0.1M Cacodylate Buffer, pH 7.4**:
   - Follow the instructions in the `Recipes for Solutions.docx` file to prepare the solution.

2. **2% Osmium (0.2M)**:
     - Follow the instructions in the `Recipes for Solutions.docx` file to prepare the solution.
  
3. **Epon**:
     - Follow the instructions in the `Recipes for Solutions.docx` file to prepare the solution.
  
4. **1% Paraphenylenediamine (PPD) in 1:1 methanol in 2-propanol**:
     - Follow the instructions in the `Recipes for Solutions.docx` file to prepare the solution.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Dependencies

### ImageJ
- **License**: ImageJ is open-source software distributed under the Public Domain.
- **Usage**: You can freely use, modify, and distribute ImageJ. For more information, visit the [ImageJ website](https://imagej.nih.gov/ij/).

### NI LabVIEW
- **License**: NI LabVIEW is proprietary software distributed by National Instruments.
- **Usage**: A valid license from National Instruments is required to use LabVIEW. For more information, visit the [NI LabVIEW website](https://www.ni.com/en-us/shop/labview.html).

**Note**: The use of the tools provided in this repository with NI LabVIEW requires a valid LabVIEW license from National Instruments. ImageJ plugins are open-source and can be freely used and modified in accordance with their respective licenses.

## Authors and Acknowledgements

- **Authors of Manuscript** - Amy N. Stahl, Elisabeth Artis, Purnima Ghose, Tonia S. Rex
- **Contributors to Files** - Thanks to all contributors who helped create the files associated with this project: Alexandra Watkins (.vi files) 

## Contact Information

For support or inquiries, please contact tonia.rex@vumc.org.
