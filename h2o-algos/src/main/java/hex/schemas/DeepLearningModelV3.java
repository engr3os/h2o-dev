package hex.schemas;

import hex.deeplearning.DeepLearningModel;
import water.Key;
import water.api.*;

public class DeepLearningModelV3 extends ModelSchema<DeepLearningModel, DeepLearningModelV3, DeepLearningModel.DeepLearningParameters, DeepLearningV3.DeepLearningParametersV3, DeepLearningModel.DeepLearningModelOutput, DeepLearningModelV3.DeepLearningModelOutputV3> {

  public static final class DeepLearningModelOutputV3 extends ModelOutputSchema<DeepLearningModel.DeepLearningModelOutput, DeepLearningModelOutputV3> {
    @API(help="Epoch Counter", direction=API.Direction.OUTPUT)
    public double epoch_counter;

    @API(help="Training Samples", direction=API.Direction.OUTPUT)
    public long training_samples;

    @API(help="Training Time (milliseconds)", direction=API.Direction.OUTPUT)
    public long training_time_ms;

    // training/validation sets
    @API(help="Score for training samples", direction=API.Direction.OUTPUT)
    public long score_training_samples;

    @API(help="Score for validation samples", direction=API.Direction.OUTPUT)
    public long score_validation_samples;

    @API(help="Model summary")
    TwoDimTableBase model_summary;

    @API(help="Whether the model is an autoencoder")
    boolean autoencoder;

    @API(help="Frame keys for weight matrices")
    KeyV3[] weights;

    @API(help="Frame keys for bias vectors")
    KeyV3[] biases;

    @API(help="Scoring history")
    TwoDimTableBase scoring_history;

    @API(help="Training data model metrics")
    ModelMetricsBase training_metrics;

    @API(help="Validation data model metrics")
    ModelMetricsBase validation_metrics;

    @API(help="Variable Importances")
    TwoDimTableBase variable_importances;

    @API(help="Training time (seconds)")
    double run_time;
  } // DeepLearningModelOutputV2

  // TODO: I think we can implement the following two in ModelSchema, using reflection on the type parameters.
  public DeepLearningV3.DeepLearningParametersV3 createParametersSchema() { return new DeepLearningV3.DeepLearningParametersV3(); }
  public DeepLearningModelOutputV3 createOutputSchema() { return new DeepLearningModelOutputV3(); }

  //==========================
  // Custom adapters go here

  // Version&Schema-specific filling into the impl
  @Override public DeepLearningModel createImpl() {
    DeepLearningModel.DeepLearningParameters parms = parameters.createImpl();
    return new DeepLearningModel(Key.make() /*dest*/, parms, new DeepLearningModel.DeepLearningModelOutput(null), null, null);
  }
}
