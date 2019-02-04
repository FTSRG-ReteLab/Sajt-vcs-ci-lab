package hu.bme.mit.train.tachograph;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class TrainTachograph {


    Table<Date, Integer, Integer> data = HashBasedTable.create();


    public void fillData() {
        data.put(new Date(), 3, 15);
        data.put(new Date(), -3, 30);
    }

    public int getCount() {
        return data.size();
    }

}
