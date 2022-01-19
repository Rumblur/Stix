package by.rumblur.stix

import club.minnced.discord.webhook.send.WebhookMessageBuilder
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener(private val plugin: StixPlugin) : Listener {

    private val builder = WebhookMessageBuilder()

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        if (event.message.startsWith("/")) return
        if (event.message.contains("@everyone") || event.message.contains("@here")) return
        builder.setUsername(event.player.name)
        builder.setAvatarUrl("https://mc-heads.net/avatar/${event.player.name}.png")
        builder.setContent(event.message)
        plugin.webhookClient.send(builder.build())
    }

}