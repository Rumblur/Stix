package by.rumblur.stix

import club.minnced.discord.webhook.WebhookClient
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class StixPlugin : JavaPlugin() {

    lateinit var config: StixConfig
    lateinit var webhookClient: WebhookClient

    override fun onEnable() {
        config = StixConfigLoader.load(dataFolder, StixConfig::class, "config")
        webhookClient = WebhookClient.withUrl(config.webhookUrl)
        Bukkit.getPluginManager().registerEvents(ChatListener(this), this)
        logger.log(Level.INFO, "Enabled, everything is fine.")
    }

    override fun onDisable() {
        webhookClient.close()
        logger.log(Level.INFO, "You disabled me. Fine. Fine...")
    }

}