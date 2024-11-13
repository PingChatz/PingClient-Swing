package interface_adapter;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
// TODO: overwrite with our code
public class ViewManagerModel extends ViewModel<String>
{

    public ViewManagerModel()
    {
        super("view manager");
        this.setState("");
    }

}
