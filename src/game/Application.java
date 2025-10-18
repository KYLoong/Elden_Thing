package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.items.BloodroseSeed;
import game.items.InheritreeSeed;
import game.grounds.Blight;
import game.grounds.Floor;
import game.grounds.Soil;
import game.grounds.Portals;
import game.grounds.Wall;
import game.items.Talisman;
import game.npc.enemy.boss.BedOfChaos;
import game.npc.creature.GoldenBeetle;
import game.npc.creature.OmenSheep;
import game.npc.creature.SpiritGoat;
import game.npc.enemy.Guts;
import game.npc.merchant.MerchantKale;
import game.npc.merchant.Sellen;
import game.items.farming.Shovel;
import game.items.farming.WateringCan;
import game.items.farming.Fertilizer;
import game.behaviours.RandomSelectionStrategy;

/**
 * The main class to setup and run the game.
 * @author Adrian Kristanto
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil());

        List<String> valleyMap = Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");

        List<String> limveldMap = Arrays.asList(
                ".............xxxx",
                "..............xxx",
                "................x",
                ".................",
                "................x",
                "...............xx",
                "..............xxx",
                "..............xxx",
                "..............xxx",
                ".............xxxx",
                ".............xxxx",
                "....xxx.....xxxxx",
                "....xxxx...xxxxxx");

        GameMap valleyGameMap = new GameMap("Valley of the Inheritree", groundFactory, valleyMap);
        GameMap limveldGameMap = new GameMap("Limveld", groundFactory, limveldMap);

        world.addGameMap(valleyGameMap);
        world.addGameMap(limveldGameMap);

        // Create a portal
        Portals valleyPortal = new Portals();
        Portals limveldPortal = new Portals();

        // Place portals on the map
        valleyGameMap.at(14, 14).setGround(valleyPortal);
        limveldGameMap.at(5, 11).setGround(limveldPortal);

        // Set the portal destination
        valleyPortal.addDestination("Limveld", limveldGameMap.at(5, 11));
        limveldPortal.addDestination("Valley of the Inheritree", valleyGameMap.at(14, 14));

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100);
        world.addPlayer(player, valleyGameMap.at(23, 10));


        player.addItemToInventory(new InheritreeSeed());
        player.addItemToInventory(new BloodroseSeed());
        player.addItemToInventory(new Shovel());
        player.addItemToInventory(new WateringCan());
        player.addItemToInventory(new Fertilizer());

        // Valley setup
        OmenSheep omenSheepPriority = new OmenSheep();
        SpiritGoat spiritGoatPriority = new SpiritGoat();
        GoldenBeetle goldenBeetlePriority = new GoldenBeetle();

        valleyGameMap.at(12, 10).addActor(omenSheepPriority);
        valleyGameMap.at(23, 14).addActor(spiritGoatPriority);
        valleyGameMap.at(23, 8).addActor(goldenBeetlePriority);

        valleyGameMap.at(26, 11).addActor(new Sellen());
        valleyGameMap.at(24, 11).addItem(new Talisman());
        valleyGameMap.at(27, 11).addActor(new MerchantKale());
        valleyGameMap.at(26, 10).addActor(new Guts());

        // Limveld setup
        OmenSheep omenSheepRandom = new OmenSheep();
        SpiritGoat spiritGoatRandom = new SpiritGoat();
        GoldenBeetle goldenBeetleRandom = new GoldenBeetle();

        // Added a follow-the-player behavior for the Golden Beetle
        goldenBeetleRandom.setPlayerToFollow(player);

        // Set to a random behavior selection strategy
        omenSheepRandom.setBehaviourSelectionStrategy(new RandomSelectionStrategy());
        spiritGoatRandom.setBehaviourSelectionStrategy(new RandomSelectionStrategy());
        goldenBeetleRandom.setBehaviourSelectionStrategy(new RandomSelectionStrategy());

        limveldGameMap.at(8, 5).addActor(omenSheepRandom);
        limveldGameMap.at(3, 8).addActor(spiritGoatRandom);
        limveldGameMap.at(7, 3).addActor(goldenBeetleRandom);
        limveldGameMap.at(5,9).addActor(new BedOfChaos());
        world.run();
    }
}

