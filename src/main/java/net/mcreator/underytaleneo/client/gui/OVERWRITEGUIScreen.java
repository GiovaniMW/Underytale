
package net.mcreator.underytaleneo.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.underytaleneo.world.inventory.OVERWRITEGUIMenu;
import net.mcreator.underytaleneo.network.OVERWRITEGUIButtonMessage;
import net.mcreator.underytaleneo.UnderytaleneoMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class OVERWRITEGUIScreen extends AbstractContainerScreen<OVERWRITEGUIMenu> {
	private final static HashMap<String, Object> guistate = OVERWRITEGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox lovesetup;
	EditBox exp;
	EditBox maxexp;

	public OVERWRITEGUIScreen(OVERWRITEGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		lovesetup.render(ms, mouseX, mouseY, partialTicks);
		exp.render(ms, mouseX, mouseY, partialTicks);
		maxexp.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/overwrite.png"));
		this.blit(ms, this.leftPos + -125, this.topPos + -37, 0, 0, 256, 256, 256, 256);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (lovesetup.isFocused())
			return lovesetup.keyPressed(key, b, c);
		if (exp.isFocused())
			return exp.keyPressed(key, b, c);
		if (maxexp.isFocused())
			return maxexp.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		lovesetup.tick();
		exp.tick();
		maxexp.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "exp", -113, 25, -1);
		this.font.draw(poseStack, "max exp", -117, 57, -1);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		lovesetup = new EditBox(this.font, this.leftPos + -76, this.topPos + -10, 120, 20, new TextComponent(""));
		guistate.put("text:lovesetup", lovesetup);
		lovesetup.setMaxLength(32767);
		this.addWidget(this.lovesetup);
		this.addRenderableWidget(new Button(this.leftPos + 58, this.topPos + -9, 30, 20, new TextComponent("SET"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new OVERWRITEGUIButtonMessage(0, x, y, z));
				OVERWRITEGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		exp = new EditBox(this.font, this.leftPos + -76, this.topPos + 23, 120, 20, new TextComponent(""));
		guistate.put("text:exp", exp);
		exp.setMaxLength(32767);
		this.addWidget(this.exp);
		this.addRenderableWidget(new Button(this.leftPos + 58, this.topPos + 22, 30, 20, new TextComponent("SET"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new OVERWRITEGUIButtonMessage(1, x, y, z));
				OVERWRITEGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		maxexp = new EditBox(this.font, this.leftPos + -76, this.topPos + 53, 120, 20, new TextComponent(""));
		guistate.put("text:maxexp", maxexp);
		maxexp.setMaxLength(32767);
		this.addWidget(this.maxexp);
		this.addRenderableWidget(new Button(this.leftPos + 58, this.topPos + 54, 30, 20, new TextComponent("SET"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new OVERWRITEGUIButtonMessage(2, x, y, z));
				OVERWRITEGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
