package net.openaudiomc.jclient.modules.player.listeners;

import net.openaudiomc.jclient.OpenAudioMc;
import net.openaudiomc.jclient.modules.player.objects.AudioListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpeakerPlaceBreakListener implements Listener {

    private String prefix = ChatColor.BLUE + "ImaginearsAudio " + ChatColor.GRAY;

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.PLAYER_HEAD) {
        		Skull skull = (Skull) event.getBlock().getState();
            if (skull.hasOwner()) {
                if (skull.getOwningPlayer().getName().toString().equalsIgnoreCase("OpenAudioMc")) {
                    AudioListener l = OpenAudioMc.getInstance().getPlayerModule().getListeners().get(event.getPlayer().getName());
                    if (l.getPlacingSpeaker() != null) {
                        event.getPlayer().sendMessage(prefix + "Registering speaker... This can take a few seconds...");
                        OpenAudioMc.getInstance().getMediaModule().placeSpeaker(event.getBlock().getLocation(), l.getPlacingSpeaker());
                        event.getPlayer().sendMessage(prefix + "Created a speaker.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.PLAYER_HEAD) {
            Skull skull = (Skull) event.getBlock().getState();
            if (skull.hasOwner()) {
                if (skull.getOwningPlayer().getName().toString().equalsIgnoreCase("OpenAudioMc")) {
                    event.getPlayer().sendMessage(prefix + "Deleting a speaker...");
                    OpenAudioMc.getInstance().getMediaModule().destroySpeaker(event.getBlock().getLocation());
                    event.getPlayer().sendMessage(prefix + "Deleted a speaker.");
                }
            }
        }
    }
    
}
