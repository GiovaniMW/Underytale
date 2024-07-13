
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

import net.mcreator.underytaleneo.world.inventory.StarterMenuMenu;
import net.mcreator.underytaleneo.procedures.PERSEVERANCEProcedure;
import net.mcreator.underytaleneo.procedures.PATIENCEProcedure;
import net.mcreator.underytaleneo.procedures.KINDNESSProcedure;
import net.mcreator.underytaleneo.procedures.JUSTICEProcedure;
import net.mcreator.underytaleneo.procedures.INTEGRITYProcedure;
import net.mcreator.underytaleneo.procedures.DETERMINATIONProcedure;
import net.mcreator.underytaleneo.procedures.BRAVERYProcedure;
import net.mcreator.underytaleneo.network.StarterMenuButtonMessage;
import net.mcreator.underytaleneo.UnderytaleneoMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class StarterMenuScreen extends AbstractContainerScreen<StarterMenuMenu> {
	private final static HashMap<String, Object> guistate = StarterMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox name;

	public StarterMenuScreen(StarterMenuMenu container, Inventory inventory, Component text) {
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
		name.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_17.png"));
		this.blit(ms, this.leftPos + -31, this.topPos + -33, 0, 0, 256, 256, 256, 256);

		if (INTEGRITYProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/integrity.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (PERSEVERANCEProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/perseverance.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (DETERMINATIONProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_1.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (JUSTICEProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_2.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (KINDNESSProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_3.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (BRAVERYProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_4.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}
		if (PATIENCEProcedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_5.png"));
			this.blit(ms, this.leftPos + 87, this.topPos + 61, 0, 0, 16, 16, 16, 16);
		}

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_18.png"));
		this.blit(ms, this.leftPos + 101, this.topPos + -2, 0, 0, 100, 100, 100, 100);

		RenderSystem.setShaderTexture(0, new ResourceLocation("underytaleneo:textures/screens/pixil-frame-0_18.png"));
		this.blit(ms, this.leftPos + -38, this.topPos + -20, 0, 0, 100, 100, 100, 100);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (name.isFocused())
			return name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		name.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		if (PATIENCEProcedure.execute(entity))
			this.font.draw(poseStack, "PACI\u00CANCIA", -20, 95, -16711681);
		if (PATIENCEProcedure.execute(entity))
			this.font.draw(poseStack, "Pessoas com essa alma costumam a ser calmos e", -26, 116, -16711681);
		if (PATIENCEProcedure.execute(entity))
			this.font.draw(poseStack, "pensativos, s\u00E3o \u00F3timas em esperar e s\u00E3o \u00F3timas ", -26, 125, -16711681);
		if (PATIENCEProcedure.execute(entity))
			this.font.draw(poseStack, "em aprender por terem paci\u00EAncia pra isso.", -26, 133, -10027009);
		if (BRAVERYProcedure.execute(entity))
			this.font.draw(poseStack, "BRAVURA", -20, 95, -26317);
		if (BRAVERYProcedure.execute(entity))
			this.font.draw(poseStack, "Aqueles que possuem bravura lutam pelos seus", -26, 116, -26317);
		if (BRAVERYProcedure.execute(entity))
			this.font.draw(poseStack, "ideais e amigos, n\u00E3o perecendo no caminho e", -26, 125, -26317);
		if (BRAVERYProcedure.execute(entity))
			this.font.draw(poseStack, "agindo mesmo com o medo.", -26, 133, -26317);
		if (INTEGRITYProcedure.execute(entity))
			this.font.draw(poseStack, "INTEGRIDADE", -21, 95, -16776961);
		if (INTEGRITYProcedure.execute(entity))
			this.font.draw(poseStack, "Ser honesto consigo e com o mundo te mant\u00E9m", -26, 116, -16776961);
		if (INTEGRITYProcedure.execute(entity))
			this.font.draw(poseStack, "\u00EDntegro e inabal\u00E1vel podendo resistir ", -27, 125, -16776961);
		if (INTEGRITYProcedure.execute(entity))
			this.font.draw(poseStack, "as maiores press\u00F5es.  ", -27, 133, -16776961);
		if (PERSEVERANCEProcedure.execute(entity))
			this.font.draw(poseStack, "PERSEVERAN\u00C7A", -21, 95, -6750004);
		if (PERSEVERANCEProcedure.execute(entity))
			this.font.draw(poseStack, "Indiv\u00EDduos com essa alma s\u00E3o persistentes sobre ", -27, 116, -6750004);
		if (PERSEVERANCEProcedure.execute(entity))
			this.font.draw(poseStack, "sua moral e seus valores, n\u00E3o desistindo at\u00E9", -26, 125, -6750004);
		if (PERSEVERANCEProcedure.execute(entity))
			this.font.draw(poseStack, "que sua alma pare de ressonar.", -26, 133, -6750004);
		if (KINDNESSProcedure.execute(entity))
			this.font.draw(poseStack, "BONDADE", -20, 95, -16711936);
		if (KINDNESSProcedure.execute(entity))
			this.font.draw(poseStack, "Esses humanos s\u00E3o demasiados gentis e piedosos,", -27, 116, -16711936);
		if (KINDNESSProcedure.execute(entity))
			this.font.draw(poseStack, "s\u00E3o muito comuns como suporte para aliados", -27, 125, -16711936);
		if (KINDNESSProcedure.execute(entity))
			this.font.draw(poseStack, "mas tamb\u00E9m s\u00E3o extremamente resilientes.", -26, 133, -16711936);
		if (JUSTICEProcedure.execute(entity))
			this.font.draw(poseStack, "JUSTI\u00C7A", -23, 95, -256);
		if (JUSTICEProcedure.execute(entity))
			this.font.draw(poseStack, "Os que carregam a justi\u00E7a com sigo tendem a", -27, 116, -256);
		if (JUSTICEProcedure.execute(entity))
			this.font.draw(poseStack, "seguir fortemente sua moralidade e odiar ver", -27, 125, -256);
		if (JUSTICEProcedure.execute(entity))
			this.font.draw(poseStack, "situa\u00E7\u00F5es de pura injusti\u00E7a e desigualdade.", -27, 134, -256);
		if (DETERMINATIONProcedure.execute(entity))
			this.font.draw(poseStack, "DETERMINA\u00C7\u00C3O", -22, 95, -65536);
		if (DETERMINATIONProcedure.execute(entity))
			this.font.draw(poseStack, "Voc\u00EA escolhe seu caminho, n\u00E3o estando preso as", -27, 116, -65536);
		if (DETERMINATIONProcedure.execute(entity))
			this.font.draw(poseStack, "correntes do destino, nada pode te parar quando ", -27, 125, -65536);
		if (DETERMINATIONProcedure.execute(entity))
			this.font.draw(poseStack, "voc\u00EA est\u00E1", -27, 134, -65536);
		if (DETERMINATIONProcedure.execute(entity))
			this.font.draw(poseStack, "DETERMINADO", 62, 152, -65536);
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
		name = new EditBox(this.font, this.leftPos + 42, this.topPos + -16, 120, 20, new TextComponent(""));
		guistate.put("text:name", name);
		name.setMaxLength(32767);
		this.addWidget(this.name);
		this.addRenderableWidget(new Button(this.leftPos + 114, this.topPos + 59, 30, 20, new TextComponent(">"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StarterMenuButtonMessage(0, x, y, z));
				StarterMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 46, this.topPos + 59, 30, 20, new TextComponent("<"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StarterMenuButtonMessage(1, x, y, z));
				StarterMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 55, this.topPos + 172, 72, 20, new TextComponent("CONFIRMAR"), e -> {
			if (true) {
				UnderytaleneoMod.PACKET_HANDLER.sendToServer(new StarterMenuButtonMessage(2, x, y, z));
				StarterMenuButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
