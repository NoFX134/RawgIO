package ru.myproject.gamenewsapp.adapters.main


import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.myproject.gameNewsAPP.R
import ru.myproject.gameNewsAPP.databinding.*
import ru.myproject.gamenewsapp.model.base.ListItem
import ru.myproject.gamenewsapp.model.game.*

object MainScreenDelegates {

  fun gamesHorizontalDelegate(onItemBind:(GamesHorizontalItem)->Unit) =
    adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
      { inflater, container ->
        ItemGamesHorizontalBinding.inflate(inflater, container, false)
      }
    ) {
      val adapter = GamesHorizontalAdapter()
      binding.rvRecycleView.adapter = adapter
      bind {
        onItemBind.invoke(item)
        binding.tvTitle.text = item.title
        adapter.items = item.games
      }
    }

  fun wideProgressDelegate() =
    adapterDelegateViewBinding<ProgressWideItem, ListItem, ItemProgressWideBinding>(
      { inflater, container -> ItemProgressWideBinding.inflate(inflater, container, false) }
    ) {
    }

  fun wideGameDelegate() =
    adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
      { inflater, container -> ItemGameWideBinding.inflate(inflater, container, false) }
    ) {

      bind {
        val resources = binding.root.resources
        Glide.with(binding.root)
          .load(item.imageUrl)
          .override(
            resources.getDimensionPixelOffset(R.dimen.game_card_wide_width),
            resources.getDimensionPixelOffset(R.dimen.game_card_wide_height)
          )
          .transform(
            CenterCrop(),
            RoundedCorners(resources.getDimensionPixelOffset(R.dimen.game_card_radius))
          )
          .transition(withCrossFade())
          .into(binding.ivImageView)
        binding.title = item.title
        binding.executePendingBindings()
      }
      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.ivImageView)
      }
    }

  fun thinProgressDelegate() =
    adapterDelegateViewBinding<ProgressThinItem, ListItem, ItemProgressThinBinding>(
      { inflater, container -> ItemProgressThinBinding.inflate(inflater, container, false) }
    ) {
    }

  fun thinGameDelegate() =
    adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
      { inflater, container -> ItemGameThinBinding.inflate(inflater, container, false) }
    ) {
      bind {
        val resources = binding.root.resources
        Glide.with(binding.root)
          .load(item.imageUrl)
          .override(
            resources.getDimensionPixelOffset(R.dimen.game_card_thin_width),
            resources.getDimensionPixelOffset(R.dimen.game_card_thin_height)
          )
          .transform(
            CenterCrop(),
            RoundedCorners(resources.getDimensionPixelOffset(R.dimen.game_card_radius))
          )
          .transition(withCrossFade())
          .into(binding.ivImageView)
        binding.title = item.title
        binding.executePendingBindings()
      }
      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.ivImageView)
      }
    }

}