package net.pitan76.mvo76.screen.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.function.Consumer;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class SimpleSliderWidget extends SliderWidget {
    protected final Function<Double, Text> textGetter;
    protected final Consumer<Double> changeCallback;

    public SimpleSliderWidget(int x, int y, int width, int height, Text text, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        super(x, y, width, height, text, defaultValue);
        this.textGetter = (Double value) -> valueTextGetter.get(text, value);
        this.changeCallback = changeCallback;
        this.updateMessage();
    }
    public SimpleSliderWidget(int x, int y, int width, int height, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(x, y, width, height, TextUtil.empty(), defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(int x, int y, int width, Text text, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(x, y, width, 20, text, defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(int x, int y, int width, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(x, y, width, 20, defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(SimpleListWidget listWidget, int width, Text text, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(listWidget.getWidth() / 2 - 155, 0, width, 20, text, defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(SimpleListWidget listWidget, int width, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(listWidget, width, TextUtil.empty(), defaultValue, valueTextGetter, changeCallback);
    }
    
    public void setValue(double value) {
        super.value = value;
    }
    
    public double getValue() {
        return super.value;
    }

    @Override
    protected void updateMessage() {
        this.setMessage(this.textGetter.apply(this.getValue()));
    }

    @Override
    protected void applyValue() {
        this.changeCallback.accept(this.getValue());
    }

    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    public interface ValueTextGetter<Double> {
        Text get(Text optionText, Double value);
    }
}
