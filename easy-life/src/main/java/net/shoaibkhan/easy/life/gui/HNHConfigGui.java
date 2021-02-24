package net.shoaibkhan.easy.life.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.LiteralText;
import net.shoaibkhan.easy.life.ClientMod;
import net.shoaibkhan.easy.life.config.ELConfig;
import net.shoaibkhan.easy.life.config.SerializableConfig;
import net.shoaibkhan.easy.life.gui.widgets.ColorButton;
import net.shoaibkhan.easy.life.gui.widgets.DoubleSubmitButton;
import net.shoaibkhan.easy.life.gui.widgets.ScaleButton;
import net.shoaibkhan.easy.life.gui.widgets.SubmitButton;

public class HNHConfigGui extends LightweightGuiDescription {
    private SerializableConfig tempConfig;
    private ClientPlayerEntity player;
    private MinecraftClient client;
    
    public HNHConfigGui(ClientPlayerEntity player,MinecraftClient client){
        this.player = player;
        this.client = client;

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 120);

        WButton doneButton = new WButton(new LiteralText("Done"));
        doneButton.setOnClick(this::onDoneClick);
        root.add(doneButton, 12, 5, 7, 1);

        WLabel label = new WLabel(new LiteralText("Health n Hunger Configuration"), ClientMod.colors("red"));
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(label, 0, 0, 21, 1);

        WButton nsbutton = new WButton(new LiteralText("Back"));
        nsbutton.setOnClick(this::onBackClick);
        root.add(nsbutton, 3, 5, 7, 1);


        ColorButton pwcolor = new ColorButton("Color", 0,ELConfig.getHnhColor());
        root.add(pwcolor, 1, 1, 6, 1);

        ScaleButton pwscale = new ScaleButton("Scale", 2);
        root.add(pwscale, 8, 1, 6, 1);

        WLabel pwpos = new WLabel(new LiteralText("Position :-"), ClientMod.colors("black"));
        pwpos.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(pwpos, 1, 3, 3, 1);

        WLabel pwx = new WLabel(new LiteralText("X="));
        pwx.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(pwx, 5, 3, 1, 1);

        WTextField pwxf = new WTextField(new LiteralText("10"));
        root.add(pwxf, 6, 3, 2, 1);

        WLabel pwy = new WLabel(new LiteralText("Y="));
        pwy.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(pwy, 9, 3, 1, 1);

        WTextField pwyf = new WTextField(new LiteralText("10"));
        root.add(pwyf, 10, 3, 2, 1);

        DoubleSubmitButton pwpossubmit = new DoubleSubmitButton("Submit",pwxf,pwyf);
        root.add(pwpossubmit, 14, 3, 3, 1);
        
        root.validate(this);
    }

    private void onBackClick(){
        this.player.closeScreen();
        this.client.openScreen(new ConfigScreen(new ConfigGui(this.player,this.client)));        
    }

    private void onDoneClick() {
        this.player.closeScreen();
    }

    @Override
    public void addPainters() {
        this.rootPanel.setBackgroundPainter(BackgroundPainter.createColorful(ClientMod.colors("lightgrey")));
    }
    
}
