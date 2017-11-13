# Implementation

Here, you find some details/tips about/for the implementation.

## New Overview

An overview contains a list on the left, a detail view on the right with means to edit the data.

An example is the referee overview, visible using menu "Schiedsrichter" -> "Übersicht", or Ctrl+Alt+R.

For a new overview you have to create the following files (example overview for trainees):

- DetailsTrainee.fxml
- DetailsTraineeController.java
- ListTrainees.fxml
- ListTraineesController.java
- OverviewTraineesController.java

For the editing features you have to implement:

- TraineeEditDialog.fxml
- TraineeEditDialogController.java

If needed, you have to implement sub-edit-dialogs, such as contact edit dialog, or wish edit dialog.
