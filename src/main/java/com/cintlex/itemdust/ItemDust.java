package com.cintlex.itemdust;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;

public class ItemDust implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
			if (entity instanceof ItemEntity itemEntity) {
				if (itemEntity.getStack().getCount() > 0) {
					spawnPoofParticles(itemEntity.getPos());
				}
			}
		});
	}

	private void spawnPoofParticles(Vec3d position) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.world != null) {
			for (int i = 0; i < 2; i++) {
				client.world.addParticleClient(
						ParticleTypes.POOF,
						position.x + (Math.random() - 0.5) * 0.3,
						position.y + Math.random() * 0.2,
						position.z + (Math.random() - 0.5) * 0.3,
						0.0, 0.0, 0.0
				);
			}
		}
	}
}