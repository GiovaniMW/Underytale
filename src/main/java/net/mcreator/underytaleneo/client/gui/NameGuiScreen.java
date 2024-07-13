
package net.mcreator.underytaleneo.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.underytaleneo.world.inventory.NameGuiMenu;
import net.mcreator.underytaleneo.procedures.VLADProcedure;
import net.mcreator.underytaleneo.procedures.PIETROProcedure;
import net.mcreator.underytaleneo.procedures.LOFIERProcedure;
import net.mcreator.underytaleneo.procedures.LEONProcedure;
import net.mcreator.underytaleneo.procedures.JONATHANProcedure;
import net.mcreator.underytaleneo.procedures.IGNISProcedure;
import net.mcreator.underytaleneo.procedures.GALProcedure;
import net.mcreator.underytaleneo.procedures.DAVIProcedure;
import net.mcreator.underytaleneo.procedures.BOBProcedure;
import net.mcreator.underytaleneo.procedures.APPEARProcedure;
import net.mcreator.underytaleneo.procedures.ALVISProcedure;
import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;
import net.mcreator.underytaleneo.network.NameGuiButtonMessage;
import net.mcreator.underytaleneo.UnderytaleneoMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class NameGuiScreen extends AbstractContainerScreen<NameGuiMenu> {
	private final static HashMap<String, Object> guistate = NameGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public NameGuiScreen(NameGuiMenu container, Inventory inventory, Component text) {
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

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/menu_2.png"));
		this.blit(ms, this.leftPos + -10, this.topPos + -19, 0, 0, 200, 200, 200, 200);

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
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).NAME) + "", 60, 9, -1);
		if (APPEARProcedure.execute(entity))
			this.font.draw(poseStack, "Voc\u00EA tem certeza?", 41, 123, -1);
		if (PIETROProcedure.execute(entity))
			this.font.draw(poseStack, "\"N\u00E3o finja que sou eu,", -3, 34, -1);
		if (PIETROProcedure.execute(entity))
			this.font.draw(poseStack, "arrume outro nome.\"", 77, 46, -1);
		if (BOBProcedure.execute(entity))
			this.font.draw(poseStack, "Os 7 Bob Humanos ", 3, 35, -1);
		if (BOBProcedure.execute(entity))
			this.font.draw(poseStack, "te impedem de fazer isto.", 46, 46, -1);
		if (LEONProcedure.execute(entity))
			this.font.draw(poseStack, "\"Um nome bem popular, huh?", -3, 35, -1);
		if (LEONProcedure.execute(entity))
			this.font.draw(poseStack, " Voc\u00EA pode fazer melhor, parceiro.\"", 0, 45, -1);
		if (IGNISProcedure.execute(entity))
			this.font.draw(poseStack, "\"At\u00E9 minha v\u00F3 tem mais criatividade", -3, 35, -1);
		if (IGNISProcedure.execute(entity))
			this.font.draw(poseStack, "Inventa outro ai, seu banana\"", 5, 46, -1);
		if (ALVISProcedure.execute(entity))
			this.font.draw(poseStack, "\"Pelos meus c\u00E1lculos, voc\u00EA...", -3, 36, -1);
		if (ALVISProcedure.execute(entity))
			this.font.draw(poseStack, "\u00C9 muito burro para pensar num nome.\"", 0, 46, -1);
		if (GALProcedure.execute(entity))
			this.font.draw(poseStack, "\"Ah cara, eu at\u00E9 deixaria", -2, 35, -1);
		if (GALProcedure.execute(entity))
			this.font.draw(poseStack, "mas me disseram para te impedir. \"", 1, 47, -1);
		if (LOFIERProcedure.execute(entity))
			this.font.draw(poseStack, "\"Esse \u00E9 de algu\u00E9m muito querido ", -3, 35, -1);
		if (LOFIERProcedure.execute(entity))
			this.font.draw(poseStack, "Escolha outro, parceiro.\"", 10, 46, -1);
		if (JONATHANProcedure.execute(entity))
			this.font.draw(poseStack, "\"Ele n\u00E3o veio porque t\u00E1 estudando.", -2, 35, -1);
		if (JONATHANProcedure.execute(entity))
			this.font.draw(poseStack, "Mas ele disse para n\u00E3o deixar\"", 7, 47, -1);
		if (DAVIProcedure.execute(entity))
			this.font.draw(poseStack, "\"Nos seus sonhos pirralho\"", -1, 35, -1);
		if (VLADProcedure.execute(entity))
			this.font.draw(poseStack, "\"HAHAHA, conta outra.\"", -3, 35, -1);
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
		this.addRenderableWidget(new Button(this.leftPos + 5, this.topPos + 149, 56, 20, new TextComponent("VOLTAR"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new NameGuiButtonMessage(0, x, y, z));
				NameGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 113, this.topPos + 149, 56, 20, new TextComponent("PROCEDER"), e -> {
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (APPEARProcedure.execute(entity))
					super.render(ms, gx, gy, ticks);
			}
		});
	}
}
