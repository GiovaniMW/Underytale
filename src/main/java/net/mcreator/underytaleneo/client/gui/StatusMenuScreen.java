
package net.mcreator.underytaleneo.client.gui;

import org.jline.utils.Status;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.underytaleneo.world.inventory.StatusMenuMenu;
import net.mcreator.underytaleneo.procedures.StatutsShow4Procedure;
import net.mcreator.underytaleneo.procedures.StatusShowProcedure;
import net.mcreator.underytaleneo.procedures.StatusShow3Procedure;
import net.mcreator.underytaleneo.procedures.StatusShow2Procedure;
import net.mcreator.underytaleneo.procedures.SoulShowProcedure;
import net.mcreator.underytaleneo.procedures.SoulShow2Procedure;
import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;
import net.mcreator.underytaleneo.network.StatusMenuButtonMessage;
import net.mcreator.underytaleneo.UnderytaleneoMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class StatusMenuScreen extends AbstractContainerScreen<StatusMenuMenu> {
	private final static HashMap<String, Object> guistate = StatusMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Checkbox Status;
	Checkbox Soul;
	Checkbox Skills;
	Checkbox DistributePoints;

	public StatusMenuScreen(StatusMenuMenu container, Inventory inventory, Component text) {
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
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/menu.png"));
		this.blit(ms, this.leftPos + -86, this.topPos + -23, 0, 0, 50, 50, 50, 50);

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_14.png"));
		this.blit(ms, this.leftPos + -86, this.topPos + 25, 0, 0, 64, 128, 64, 128);

		if (StatusShowProcedure.execute(entity, guistate)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_16.png"));
			this.blit(ms, this.leftPos + 23, this.topPos + 11, 0, 0, 128, 128, 128, 128);
		}
		if (SoulShowProcedure.execute(entity, guistate)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_17.png"));
			this.blit(ms, this.leftPos + 23, this.topPos + 11, 0, 0, 128, 128, 128, 128);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).NAME) + "", -80, -19, -1);
		this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).LOVE) + "", -67, -9, -1);
		this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).HP) + "", -67, 3, -1);
		if (StatusShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).CHARISMA) + "", 77, 72, -1);
		if (StatusShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE) + "", 62, 28, -1);
		if (StatusShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).STRENGHT) + "", 82, 14, -1);
		if (StatusShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).RESISTANCE) + "", 88, 57, -1);
		if (StatusShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).AGILITY) + "", 75, 42, -1);
		if (StatusShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).POINTS) + "", 67, 117, -1);
		if (StatutsShow4Procedure.execute(entity))
			this.font.draw(poseStack, "Strenght", 199, 13, -1);
		if (StatutsShow4Procedure.execute(entity))
			this.font.draw(poseStack, "Charisma", 199, 93, -1);
		if (StatutsShow4Procedure.execute(entity))
			this.font.draw(poseStack, "Resistance", 199, 73, -1);
		if (StatutsShow4Procedure.execute(entity))
			this.font.draw(poseStack, "Agility", 199, 53, -1);
		if (StatutsShow4Procedure.execute(entity))
			this.font.draw(poseStack, "Magic", 199, 33, -1);
		if (SoulShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).NAME) + "", 56, 19, -1);
		if (SoulShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).LOVE) + "", 48, 44, -1);
		if (SoulShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).EXP) + "", 121, 44, -1);
		if (SoulShow2Procedure.execute(entity))
			this.font.draw(poseStack, "" + ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).NEXTEXP) + "", 130, 57, -1);
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
		Status = new Checkbox(this.leftPos + -8, this.topPos + 27, 20, 20, new TextComponent(""), false);
		guistate.put("checkbox:Status", Status);
		this.addRenderableWidget(Status);
		Soul = new Checkbox(this.leftPos + -8, this.topPos + 53, 20, 20, new TextComponent(""), false);
		guistate.put("checkbox:Soul", Soul);
		this.addRenderableWidget(Soul);
		Skills = new Checkbox(this.leftPos + -8, this.topPos + 79, 20, 20, new TextComponent(""), false);
		guistate.put("checkbox:Skills", Skills);
		this.addRenderableWidget(Skills);
		DistributePoints = new Checkbox(this.leftPos + 281, this.topPos + 183, 20, 20, new TextComponent(""),

				StatusShow2Procedure.execute(entity));
		guistate.put("checkbox:DistributePoints", DistributePoints);
		this.addRenderableWidget(DistributePoints);
		this.addRenderableWidget(new Button(this.leftPos + 257, this.topPos + 7, 30, 20, new TextComponent("+"), e -> {
			if (StatusShow3Procedure.execute(entity, guistate)) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StatusMenuButtonMessage(0, x, y, z));
				StatusMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (StatusShow3Procedure.execute(entity, guistate))
					super.render(ms, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(new Button(this.leftPos + 257, this.topPos + 27, 30, 20, new TextComponent("+"), e -> {
			if (StatusShow3Procedure.execute(entity, guistate)) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StatusMenuButtonMessage(1, x, y, z));
				StatusMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (StatusShow3Procedure.execute(entity, guistate))
					super.render(ms, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(new Button(this.leftPos + 257, this.topPos + 47, 30, 20, new TextComponent("+"), e -> {
			if (StatusShow3Procedure.execute(entity, guistate)) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StatusMenuButtonMessage(2, x, y, z));
				StatusMenuButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (StatusShow3Procedure.execute(entity, guistate))
					super.render(ms, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(new Button(this.leftPos + 257, this.topPos + 67, 30, 20, new TextComponent("+"), e -> {
			if (StatusShow3Procedure.execute(entity, guistate)) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StatusMenuButtonMessage(3, x, y, z));
				StatusMenuButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (StatusShow3Procedure.execute(entity, guistate))
					super.render(ms, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(new Button(this.leftPos + 257, this.topPos + 87, 30, 20, new TextComponent("+"), e -> {
			if (StatusShow3Procedure.execute(entity, guistate)) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StatusMenuButtonMessage(4, x, y, z));
				StatusMenuButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (StatusShow3Procedure.execute(entity, guistate))
					super.render(ms, gx, gy, ticks);
			}
		});
	}
}
