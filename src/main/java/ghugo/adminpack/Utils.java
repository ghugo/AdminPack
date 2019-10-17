package ghugo.adminpack;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;

public class Utils {

    public static boolean isNight(World world) {
        return world.getTime() > 12500 && world.getTime() < 23000;
    }

    public static String getCardinalDirection(final Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 45.0) {
            return "N";
        } else if (45.0 <= rotation && rotation < 135.0) {
            return "E";
        } else if (135.0 <= rotation && rotation < 225.0) {
            return "S";
        } else if (225.0 <= rotation && rotation < 315.0) {
            return "W";
        } else if (315.0 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return "N";
        }
    }

    public static Block getTargetBlock(final Player player) {
        final Block block;
        try {
            block = player.getTargetBlock((Set) null, 200);
        } catch (IllegalStateException e) {
            return null;
        }
        switch (block.getType()) {
            case AIR:
                return null;
            default:
                return block;
        }
    }
}
