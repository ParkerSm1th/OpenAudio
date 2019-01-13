package net.openaudiomc.jclient.modules.media.tasks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import net.openaudiomc.jclient.OpenAudioMc;
import net.openaudiomc.jclient.modules.player.objects.AudioListener;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegionCheck implements Runnable {

    @Override
    public void run() {
        for (AudioListener l : OpenAudioMc.getInstance().getPlayerModule().getListeners().values()) {
            if (l.getIsConnected()) {
                List<String> regions = new ArrayList<>();
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionQuery query = container.createQuery();
                ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(l.getPlayer().getLocation()));
                for(ProtectedRegion r : set) {
                    regions.add(r.getId());
                }
                l.updateRegions(regions);
            }
        }
    }
}
