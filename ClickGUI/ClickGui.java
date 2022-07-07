package dev.flerovium.ui.clickgui;

import java.io.IOException;
import java.util.ArrayList;

import dev.flerovium.module.category.Category;
import dev.flerovium.ui.clickgui.component.Component;
import dev.flerovium.ui.clickgui.component.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

/**
 *  Made by Pandus1337
 *  it's free to use,
 *  but you have to credit me
 *  @author Pandus1337
 *  <a href="https://github.com/Pandus1337/ClickGUI">...</a>
 */

public class ClickGui extends GuiScreen {

	public static ArrayList<Frame> frames;
	public static int color = -1;
	
	public ClickGui() {
		frames = new ArrayList<>();
		int frameX = 5;
		for(Category category : Category.values()) {
			Frame frame = new Frame(category);
			frame.setX(frameX);
			frames.add(frame);
			frameX += frame.getWidth() + 1;
		}
	}
	
	@Override
	public void initGui() {
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		for(Frame frame : frames) {
			frame.renderFrame(this.fontRendererObj);
			for(Component comp : frame.getComponents()) {
				comp.updateComponent(mouseX, mouseY);
			}
		}

		/*										    */ ScaledResolution s = new ScaledResolution(mc);
		/*  https://github.com/Pandus1337/ClickGUI  */ GL11.glPushMatrix();
		/*  Copyright Pandus1337		        	*/ GL11.glTranslated(s.getScaleFactor(), s.getScaledHeight(), 0);GL11.glScaled(0.5, 0.5, 0.5);
		/*  Please don't remove this                */ mc.fontRendererObj.drawStringWithShadow("Made by Pandus1337 | https://github.com/Pandus1337/ClickGUI", Minecraft.getMinecraft().fontRendererObj.getStringWidth("Made by Pandus1337 | https://github.com/Pandus1337/ClickGUI") - 317, -Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, -1);
		/*										    */ GL11.glPopMatrix();

	}

	@Override
	protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		for (Frame frame : frames) {
			if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
				frame.setDrag(true);
				frame.dragX = mouseX - frame.getX();
				frame.dragY = mouseY - frame.getY();
			}
			if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
				frame.setOpen(!frame.isOpen());
			}
			if (frame.isOpen()) {
				if (!frame.getComponents().isEmpty()) {
					for (Component component : frame.getComponents()) {
						component.mouseClicked(mouseX, mouseY, mouseButton);
					}
				}
			}
		}
	}


	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		for(Frame frame : frames) {
			if(frame.isOpen() && keyCode != 1) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.keyTyped(typedChar, keyCode);
					}
				}
			}
		}
		if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        }
	}
	
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
		for(Frame frame : frames) {
			frame.setDrag(false);
		}
		for(Frame frame : frames) {
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.mouseReleased(mouseX, mouseY, state);
					}
				}
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

}
