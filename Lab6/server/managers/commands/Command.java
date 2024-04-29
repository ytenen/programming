package managers.commands;

import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -904182559535046703L;
    private final String name;
    private final boolean hasArguments;

    protected Command(String name, boolean hasArguments) {
        this.name = name;
        this.hasArguments = hasArguments;
    }

    public String getName() {
        return name;
    }

    public Response execute(Request request){
        return new Response(null);
    };

    public boolean isHasArguments() {
        return hasArguments;
    }
}
