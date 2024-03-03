package net.pitan76.mvo76.screen.widget;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;

import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mvo76.screen.widget.SimpleListWidget.WidgetEntry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class SimpleListWidget extends ElementListWidget<WidgetEntry> {

    public SimpleListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
        super(client, width, height, top, bottom, itemHeight);
        this.centerListVertically = false;
    }

    public SimpleListWidget(MinecraftClient client, int width, int height, int y, int itemHeight) {
        this(client, width, height, y, y + height, itemHeight);
    }

    public void add(ClickableWidget widget) {
        super.addEntry(WidgetEntry.create(widget));
    }

    @Override
    public int getRowWidth() {
        return 400;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    protected int getScrollbarPositionX() {
        return super.getScrollbarPositionX() + 32;
    }

    @Nullable
    public ClickableWidget getWidget(int index) {
        if (index < 0 || index >= this.children().size()) {
            return null;
        }
        return this.children().get(index).getWidget();
    }

    public Optional<ClickableWidget> getHoveredWidget(double mouseX, double mouseY) {
        for (WidgetEntry entry : this.children()) {
            if (entry.getWidget().isMouseOver(mouseX, mouseY)) {
                return Optional.of(entry.getWidget());
            }
        }
        return Optional.empty();
    }

    @Environment(EnvType.CLIENT)
    public static class WidgetEntry extends Entry<WidgetEntry> {
        protected final ClickableWidget widget;

        public WidgetEntry(ClickableWidget widget) {
            this.widget = widget;
        }

        public static WidgetEntry create(ClickableWidget widget) {
            return new WidgetEntry(widget);
        }

        @Deprecated
        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            widget.y = y;
            widget.render(matrices, mouseX, mouseY, tickDelta);
        }

        @Deprecated
        @Override
        public List<? extends Element> children() {
            return ImmutableList.of(widget);
        }

        @Deprecated
        @Override
        public List<? extends Selectable> selectableChildren() {
            return ImmutableList.of(widget);
        }

        public ClickableWidget getWidget() {
            return widget;
        }
    }
}
